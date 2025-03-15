package org.demo.minio;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;

import javax.crypto.KeyGenerator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzs
 * @Date 2024/3/26 15:45
 **/
public class MinioUtils {

    public static void put() {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.0.34:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            /* Amazon S3: */
            // MinioClient minioClient =
            //     MinioClient.builder()
            //         .endpoint("https://s3.amazonaws.com")
            //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
            //         .build();

            // Create some content for the object.
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                builder.append(
                        "Sphinx of black quartz, judge my vow: Used by Adobe InDesign to display font samples. ");
                builder.append("(29 letters)\n");
                builder.append(
                        "Jackdaws love my big sphinx of quartz: Similarly, used by Windows XP for some fonts. ");
                builder.append("(31 letters)\n");
                builder.append(
                        "Pack my box with five dozen liquor jugs: According to Wikipedia, this one is used on ");
                builder.append("NASAs Space Shuttle. (32 letters)\n");
                builder.append(
                        "The quick onyx goblin jumps over the lazy dwarf: Flavor text from an Unhinged Magic Card. ");
                builder.append("(39 letters)\n");
                builder.append(
                        "How razorback-jumping frogs can level six piqued gymnasts!: Not going to win any brevity ");
                builder.append("awards at 49 letters long, but old-time Mac users may recognize it.\n");
                builder.append(
                        "Cozy lummox gives smart squid who asks for job pen: A 41-letter tester sentence for Mac ");
                builder.append("computers after System 7.\n");
                builder.append(
                        "A few others we like: Amazingly few discotheques provide jukeboxes; Now fax quiz Jack! my ");
                builder.append("brave ghost pled; Watch Jeopardy!, Alex Trebeks fun TV quiz game.\n");
                builder.append("---\n");
            }

            {
                // Create a InputStream for object upload.
                ByteArrayInputStream bais = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));

                // Create object 'my-objectname' in 'my-bucketname' with content from the input stream.
                minioClient.putObject(
                        PutObjectArgs.builder().bucket("test").object("20240327/normal-text.txt").stream(
                                bais, bais.available(), -1)
                                .build());
                bais.close();
                System.out.println("my-objectname is uploaded successfully");
            }

            {
                // Create a InputStream for object upload.
                ByteArrayInputStream bais = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));

                // Create headers
                Map<String, String> headers = new HashMap<>();
                // Add custom content type
                headers.put("Content-Type", "application/octet-stream");
                // Add storage class
                headers.put("X-Amz-Storage-Class", "REDUCED_REDUNDANCY");

                // Add custom/user metadata
                Map<String, String> userMetadata = new HashMap<>();
                userMetadata.put("My-Project", "Project One");

                // Create object 'my-objectname' with user metadata and other properties in 'my-bucketname'
                // with content
                // from the input stream.
                minioClient.putObject(
                        PutObjectArgs.builder().bucket("test").object("20240327/normal-text-user-metadata.txt").stream(
                                bais, bais.available(), -1)
                                .headers(headers)
                                .userMetadata(userMetadata)
                                .build());
                bais.close();
                System.out.println("my-objectname is uploaded successfully");
            }

            {
                // Create object name ending with '/' (mostly called folder or directory).
                minioClient.putObject(
                        PutObjectArgs.builder().bucket("test").object("level-one/son-first/").stream(
                                new ByteArrayInputStream(new byte[]{}), 1, -1)
                                .build());
                System.out.println("path/to/ is created successfully");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public static void putWithEncryption() throws Exception {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://192.168.0.34:9000")
                        .credentials("minioadmin", "minioadmin")
                        .build();
        // Create a InputStream for object upload.
        FileInputStream fis = new FileInputStream("E:\\data\\photos\\装饰画5.jpeg");

        {

            ServerSideEncryption sseS3 = new ServerSideEncryptionS3();

            // Create encrypted object 'my-objectname' using SSE-S3 in 'my-bucketname' with content
            // from the input stream.
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("test").object("20240327/normal-text-sseS3.txt").stream(
                            fis, fis.available(), -1)
                            .sse(sseS3)
                            .build());
            fis.close();
            System.out.println("my-objectname is uploaded successfully");
        }
    }

    public static void putWithSSL() throws Exception {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://192.168.0.34:9000")
                        .credentials("minioadmin", "minioadmin")
                        .build();
        // Create a InputStream for object upload.
        FileInputStream fis = new FileInputStream("E:\\data\\photos\\装饰画5.jpeg");
        {

            // Generate a new 256 bit AES key - This key must be remembered by the client.
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            ServerSideEncryptionCustomerKey ssec =
                    new ServerSideEncryptionCustomerKey(keyGen.generateKey());

            // Create encrypted object 'my-objectname' using SSE-C in 'my-bucketname' with content from
            // the input stream.
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("test").object("20240327/normal-text-ssec.txt").stream(
                            fis, fis.available(), -1)
                            .sse(ssec)
                            .build());
            fis.close();
            System.out.println("my-objectname is uploaded successfully");
        }

        {

            Map<String, String> myContext = new HashMap<>();
            myContext.put("key1", "value1");
            ServerSideEncryption sseKms = new ServerSideEncryptionKms("Key-Id", myContext);

            // Create encrypted object 'my-objectname' using SSE-KMS in 'my-bucketname' with content
            // from the input stream.
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("test").object("20240327/normal-text-sseKms.txt").stream(
                            fis, fis.available(), -1)
                            .sse(sseKms)
                            .build());
            fis.close();
            System.out.println("my-objectname is uploaded successfully");
        }
    }

    public static void upload() {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.0.34:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
            } else {
                System.out.println("Bucket 'asiatrip' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("asiatrip")
                            .object("装饰画5.jpeg")
                            .filename("E:\\data\\photos\\装饰画5.jpeg")
                            .build());
            System.out.println(
                    "'E:\\data\\photos\\装饰画5.jpeg' is successfully uploaded as "
                            + "object '装饰画5.jpeg' to bucket 'asiatrip'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void get() {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.0.34:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            /* Amazon S3: */
            // MinioClient minioClient =
            //     MinioClient.builder()
            //         .endpoint("https://s3.amazonaws.com")
            //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
            //         .build();

            // Get input stream to have content of 'my-objectname' from 'my-bucketname'
            InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket("test").object("20240327/normal-text.txt").build());

            // Read the input stream and print to the console till EOF.
            byte[] buf = new byte[16384];
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
            }

            // Close the input stream.
            stream.close();
        } catch (MinioException | IOException e) {
            System.out.println("Error occurred: " + e);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static void getUrl() {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.0.34:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            /* Amazon S3: */
            // MinioClient minioClient =
            //     MinioClient.builder()
            //         .endpoint("https://s3.amazonaws.com")
            //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
            //         .build();

            // Get presigned URL string to delete 'my-objectname' in 'my-bucketname' and its life time
            // is one day.
            String url =
                    minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket("asiatrip")
                                    .object("装饰画5.jpeg")
                                    .expiry(60)
                                    .build());
            System.out.println(url);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.0.34:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            /* Amazon S3: */
            // MinioClient minioClient =
            //     MinioClient.builder()
            //         .endpoint("https://s3.amazonaws.com")
            //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
            //         .build();

            // Remove object.
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());

            // Remove versioned object.
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket("my-bucketname")
                            .object("my-versioned-objectname")
                            .versionId("my-versionid")
                            .build());

            // Remove versioned object bypassing Governance mode.
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket("my-bucketname")
                            .object("my-versioned-objectname")
                            .versionId("my-versionid")
                            .bypassGovernanceMode(true)
                            .build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            e.printStackTrace();
        }
    }

}

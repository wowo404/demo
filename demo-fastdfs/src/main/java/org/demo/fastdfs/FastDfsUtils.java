package org.demo.fastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @author lzs
 * @Date 2024/3/29 15:46
 **/
public class FastDfsUtils {

    public static String upload(String filePath, String tagName, String tagValue) throws IOException, MyException {
//        ClientGlobal.init("fastdfs-client.conf");//文件后缀不一样
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
        NameValuePair nameValuePair = new NameValuePair(tagName, tagValue);
        String result = storageClient1.upload_file1(filePath, null, new NameValuePair[]{nameValuePair});
        if (null == result) {
            throw new RuntimeException("上传失败");
        }
        return result;
    }

    public static void delete(String fileId) throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
        int success = storageClient1.delete_file1(fileId);
        if (success == 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    public static void get(String fileId) throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
        FileInfo fileInfo = storageClient1.get_file_info1(fileId);
        System.out.println(fileInfo);
    }

    public static void metadata(String fileId) throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
        NameValuePair[] metadata = storageClient1.get_metadata1(fileId);
        for (NameValuePair pair : metadata) {
            System.out.println(pair.getName() + " " + pair.getValue());
        }
    }

    public static void download(String fileId, String dest) throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
        int success = storageClient1.download_file1(fileId, dest);
        if (success == 0) {
            System.out.println("下载成功");
        } else {
            System.out.println("下载失败");
        }
    }

}

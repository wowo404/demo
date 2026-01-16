package org.liu.hutool;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 发送http请求，加解密
 */
public class TestEncryptDecrypt {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 200, 0L,
            java.util.concurrent.TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5000));

    public static void main(String[] args) throws InterruptedException {
        TestEncryptDecrypt testEncryptDecrypt = new TestEncryptDecrypt();
//        testEncryptDecrypt.auth();
//        testEncryptDecrypt.queryList();
//        testEncryptDecrypt.get();
//        testEncryptDecrypt.createToken();
        testEncryptDecrypt.batchCreateToken();
    }

    public void get() {
        //2008075421379211264
        String data = "{" +
                "\"requestNo\": \"\"," +
                "\"acceptNo\": \"2008075427477749760\"" +
                "}";
        String url = "http://127.0.0.1:52131/openapi/token/get";
        doRequest(data, url);
    }

    public void queryList() {
        String data = "{" +
                "\"deviceNo\": \"00000001\"," +
                "\"startTime\": \"2026-01-01T19:09:40+08:00\"," +
                "\"endTime\": \"2026-01-30T19:09:40+08:00\"" +
                "}";
        String url = "http://127.0.0.1:52131/openapi/token/queryList";
        doRequest(data, url);
    }

    public void batchCreateToken() throws InterruptedException {
        int totalRequests = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(totalRequests);
        LongAdder successCount = new LongAdder();
        AtomicLong totalDurationNs = new AtomicLong(0);
        long startTestTime = System.currentTimeMillis();
        for (int i = 0; i < totalRequests; i++) {
            executor.execute(() -> {
                long start = System.nanoTime();
                try {
                    createToken();
                    successCount.increment();
                } catch (Exception e) {
                    System.err.println("Request failed: " + e.getMessage());
                } finally {
                    totalDurationNs.addAndGet(System.nanoTime() - start);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        long endTestTime = System.currentTimeMillis();
        executor.shutdown();
        // Calculations
        double totalTestTimeSec = (endTestTime - startTestTime) / 1000.0;
        double avgLatencyMs = (totalDurationNs.get() / (double) totalRequests) / 1_000_000.0;
        double qps = totalRequests / totalTestTimeSec;

        // Results
        System.out.println("--------------------------------------");
        System.out.println("Test Results:");
        System.out.printf("Total Time:     %.2f s\n", totalTestTimeSec);
        System.out.printf("Success Ratio:  %d/%d\n", successCount.sum(), totalRequests);
        System.out.printf("Average Latency: %.2f ms\n", avgLatencyMs);
        System.out.printf("QPS (Throughput): %.2f req/s\n", qps);
        System.out.println("--------------------------------------");
    }

    public void createToken() {
        String requestNo = IdUtil.getSnowflakeNextIdStr();
        String deviceNo = RandomUtil.randomEle(Arrays.asList("00000001", "00000002", "00000003", "00000004", "00000005",
                "00000006", "00000007", "00000008", "00000009", "00000010"));
        int days = RandomUtil.randomInt(1, 30);
        String data = "{" +
                "\"requestNo\": \"" + requestNo + "\"," +
                "\"notifyUrl\": \"\"," +
                "\"deviceNo\": \"" + deviceNo + "\"," +
                "\"protocolName\": \"12位协议\"," +
                "\"tokenType\": 0," +
                "\"data\": {" +
                "\"days\": " + days +
                "}," +
                "\"attach\": \"\"" +
                "}";
        String url = "http://118.178.142.156:53010/prod-api/openapi/token/v1/create";
        doRequest(data, url);
    }

    public void auth() {
        String data = "{\"clientId\":\"e5cd7e4891bf95d1d19206ce24a7b32e\",\"grantType\":\"password\",\"tenantId\":\"000000\",\"sourceType\":\"partner\",\"username\":\"admin\",\"password\":\"admin123\"}";
        String url = "http://127.0.0.1:52131/auth/login";
        doRequest(data, url);
    }

    public void doRequest(String data, String url) {
        //这是平台的密钥对（合作方拥有平台的公钥）
        String platformRasPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKoR8mX0rGKLqzcWmOzbfj64K8ZIgOdHnzkXSOVOZbFu/TJhZ7rFAN+eaGkl3C4buccQd/EjEsj9ir7ijT7h96MCAwEAAQ==";
        String platformRasPrivateKey = "";
        //这是合作方的密钥对（平台拥有合作方的公钥）
        String partnerRasPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJnNwrj4hi/y3CCJu868ghCG5dUj8wZK++RNlTLcXoMmdZWEQ/u02RgD5LyLAXGjLOjbMtC+/J9qofpSGTKSx/MCAwEAAQ==";
        String partnerRasPrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmc3CuPiGL/LcIIm7zryCEIbl1SPzBkr75E2VMtxegyZ1lYRD+7TZGAPkvIsBcaMs6Nsy0L78n2qh+lIZMpLH8wIDAQABAkEAk82Mhz0tlv6IVCyIcw/s3f0E+WLmtPFyR9/WtV3Y5aaejUkU60JpX4m5xNR2VaqOLTZAYjW8Wy0aXr3zYIhhQQIhAMfqR9oFdYw1J9SsNc+CrhugAvKTi0+BF6VoL6psWhvbAiEAxPPNTmrkmrXwdm/pQQu3UOQmc2vCZ5tiKpW10CgJi8kCIFGkL6utxw93Ncj4exE/gPLvKcT+1Emnoox+O9kRXss5AiAMtYLJDaLEzPrAWcZeeSgSIzbL+ecokmFKSDDcRske6QIgSMkHedwND1olF8vlKsJUGK3BcdtM8w4Xq7BpSBwsloE=";
        String languageFlag = "content-language";
        String language = "en_US";
        String timezoneFlag = "X-Timezone-Id";
        String timezone = "Asia/Shanghai";
        String tenantFlag = "tenant-id";
        String tenantId = "000000";
        String headerFlag = "encrypt-key";
        String aesKey = getAesKey();
        System.out.println("原始aesKey：" + aesKey);
        String encryptAesKey = encryptAesKey(platformRasPublicKey, aesKey);
        System.out.println("加密后的aesKey：" + encryptAesKey);
        String encryptedBody = encryptBody(data, aesKey);
        String json = "{\"body\":\"" + encryptedBody + "\"}";
        System.out.println("加密后的请求体：" + encryptAesKey);
        try (HttpResponse response = HttpUtil.createPost(url)
                .header(languageFlag, language)
                .header(timezoneFlag, timezone)
                .header(tenantFlag, tenantId)
                .header(headerFlag, encryptAesKey)
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(json)
                .execute()) {
            if (response.isOk()) {
                String encryptedAesKey = response.header(headerFlag);
                String responseBody = response.body();
                System.out.println("响应头中的aesKey（解密前）：" + encryptedAesKey);
                System.out.println("响应内容（解密前）：" + responseBody);
                if (StrUtil.isNotBlank(encryptedAesKey)) {
                    String rawAesKey = decryptAesKey(partnerRasPrivateKey, encryptedAesKey);
                    String realBody = JSONUtil.parseObj(responseBody).get("body", String.class);
                    String decryptBody = decryptBody(realBody, rawAesKey);
                    System.out.println("响应头中的aesKey（解密后）：" + rawAesKey);
                    System.out.println("响应内容（解密后）：" + decryptBody);
                }
            }
        }
    }

    public String getAesKey() {
        return RandomUtil.randomString(32);
    }

    public String encryptAesKey(String rsaPublicKey, String aesKey) {
        // 秘钥使用 Base64 编码
        String encryptAes = Base64.getEncoder().encodeToString(aesKey.getBytes(StandardCharsets.UTF_8));
        // Rsa 公钥加密 Base64 编码
        return SecureUtil.rsa(null, rsaPublicKey).encryptBase64(encryptAes, KeyType.PublicKey);
    }

    public String decryptAesKey(String rsaPrivateKey, String encryptedAesKey) {
        // 获取 AES 密码 采用 RSA 加密
        String decryptAes = SecureUtil.rsa(rsaPrivateKey, null).decryptStr(encryptedAesKey, KeyType.PrivateKey, StandardCharsets.UTF_8);
        // base64解码 AES 密码
        return new String(Base64.getDecoder().decode(decryptAes));
    }

    public String encryptBody(String body, String aesKey) {
        // 对内容进行加密
        return SecureUtil.aes(aesKey.getBytes(StandardCharsets.UTF_8)).encryptBase64(body, StandardCharsets.UTF_8);
    }

    public String decryptBody(String body, String aesKey) {
        // 解密 body
        return SecureUtil.aes(aesKey.getBytes(StandardCharsets.UTF_8)).decryptStr(body, StandardCharsets.UTF_8);
    }

}

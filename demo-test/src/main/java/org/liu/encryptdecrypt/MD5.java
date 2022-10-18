package org.liu.encryptdecrypt;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.liu.model.ChannelProductReq;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Created by hello on 2017/11/9.
 */
public class MD5 {

    public static final String ALGORITHM = "MD5";
    public static final String CHARSET = "UTF-8";

    public static String encrypt(String content) throws Exception {
        // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(CHARSET));
        messageDigest.update(buffer);
        // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
        byte[] digest = messageDigest.digest();
        // 把密文转换成十六进制的字符串形式
        return Base64.getEncoder().encodeToString(digest);
    }

    public static void main(String[] args) throws Exception {
        testProductRequest();
    }

    public static void testProductRequest() throws Exception {
        ChannelProductReq req = new ChannelProductReq();
        req.setUserId("abcd123457");
        req.setPhone("15058121294");

        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnvjRgZGTp5iFWjMeah9UnAAIUPFQJS0/ung6Hd3hs8dR54EA2hAWUJPgj2uDYSqzQrjpqY96UxlnibLALwWdUVIvYJD2MjJocZ8b3shGE8ZZNtaK/6c4v52Ql9svjVG+s3hTasUWmONgupyTgUExreRGfbwhxZwdrwkKofT17W17XQyxOSssDc9FiKHp4HZmpv1Suc8uDXkgexIXd4A+xxniGcX/iPvu1MF0JWntaPQ/XBLoo8Xys1nnfW1t5eL1IeBhJEWD8JJD2BFTC16i43/co4Cc0b3PPWNlsTgn6RFklevNkRLpRcrre9f9WxX/0IyTl9/fub8jfbpT/5VJeQIDAQAB";
        String data = RSA.encrypt(JSON.toJSONString(req), publicKey);
        System.out.println("encrypt data:" + data);
        long timestamp = System.currentTimeMillis();
        System.out.println("timestamp:" + timestamp);
        String salt = "y+2-4.post7&%]#s345[vr";
        String str = StrUtil.join("", "algorithm", "RSA", "channelCode", "QLZD", "data", data,
                "service", "openapi-service", "timestamp", timestamp, salt);
        System.out.println("sign:" + encrypt(str).toUpperCase());
    }

}

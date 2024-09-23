package algorithm.basic;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * jdk中没有实现的算法
 * 官方网站：https://www.bouncycastle.org/download/bouncy-castle-java/
 *
 * @author lzs
 * @Date 2024/9/20 15:05
 **/
public class BouncyCastle {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 注册BouncyCastle:
        Security.addProvider(new BouncyCastleProvider());
        // 按名称正常调用:
        MessageDigest md = MessageDigest.getInstance("RipeMD160");
        md.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        System.out.println(new String(result));
    }

}

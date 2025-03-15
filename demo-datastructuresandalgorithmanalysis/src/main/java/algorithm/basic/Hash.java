package algorithm.basic;

import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 哈希算法
 * java支持的哈希算法：https://docs.oracle.com/en/java/javase/21/docs/specs/security/standard-names.html#messagedigest-algorithms
 *
 * @author lzs
 * @Date 2024/9/20 14:49
 **/
public class Hash {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update("Hello".getBytes());
        md5.update("World".getBytes());
        byte[] digest = md5.digest();
        System.out.println(Hex.encode(digest));
    }

}

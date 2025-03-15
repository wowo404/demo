package algorithm.basic;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密算法
 * RSA
 *
 * @author lzs
 * @Date 2024/9/20 15:28
 **/
public class AsymmetricEncryption {
    public static void main(String[] args) throws Exception {
        // 明文:
        byte[] plain = "Hello, encrypt use RSA".getBytes("UTF-8");
        // 创建公钥／私钥对:
        Person alice = new Person("Alice");
        // 用Alice的公钥加密:
        byte[] pk = alice.getPublicKey();
        System.out.println("public key: " + Hex.toHexString(pk));
        byte[] encrypted = alice.encrypt(plain);
        System.out.println("encrypted: " + Hex.toHexString(encrypted));
        // 用Alice的私钥解密:
        byte[] sk = alice.getPrivateKey();
        System.out.println("private key: " + Hex.toHexString(sk));
        byte[] decrypted = alice.decrypt(encrypted);
        System.out.println(new String(decrypted, "UTF-8"));
    }

    static class Person {
        String name;
        // 私钥:
        PrivateKey sk;
        // 公钥:
        PublicKey pk;

        public Person(String name) throws GeneralSecurityException {
            this.name = name;
            // 生成公钥／私钥对:
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
            kpGen.initialize(1024);
            KeyPair kp = kpGen.generateKeyPair();
            this.sk = kp.getPrivate();
            this.pk = kp.getPublic();
        }

        // 把私钥导出为字节
        public byte[] getPrivateKey() {
            return this.sk.getEncoded();
        }

        // 把公钥导出为字节
        public byte[] getPublicKey() {
            return this.pk.getEncoded();
        }

        public PublicKey bytesToPublicKey(byte[] pkData) throws NoSuchAlgorithmException, InvalidKeySpecException {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 恢复公钥:
            X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(pkData);
            return keyFactory.generatePublic(pkSpec);
        }

        public PrivateKey bytesToPrivateKey(byte[] skData) throws NoSuchAlgorithmException, InvalidKeySpecException {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 恢复私钥:
            PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(skData);
            return keyFactory.generatePrivate(skSpec);
        }

        // 用公钥加密:
        public byte[] encrypt(byte[] message) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.pk);
            return cipher.doFinal(message);
        }

        // 用私钥解密:
        public byte[] decrypt(byte[] input) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, this.sk);
            return cipher.doFinal(input);
        }
    }
}

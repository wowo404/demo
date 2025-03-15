package algorithm.basic;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * 签名算法
 * RSA：
 *     MD5withRSA
 *     SHA1withRSA
 *     SHA256withRSA
 * DSA：
 *     SHA1withDSA
 *     SHA256withDSA
 *     SHA512withDSA
 * ECDSA：比特币采用了此签名算法
 *
 * @author lzs
 * @Date 2024/9/20 15:55
 **/
public class SignatureAlgorithm {

    public static void main(String[] args) throws GeneralSecurityException {
        // 生成RSA公钥/私钥:
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair kp = kpGen.generateKeyPair();
        PrivateKey sk = kp.getPrivate();
        PublicKey pk = kp.getPublic();

        // 待签名的消息:
        byte[] message = "Hello, I am Bob!".getBytes(StandardCharsets.UTF_8);

        // 用私钥签名:
        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(sk);
        s.update(message);
        byte[] signed = s.sign();
        System.out.println("signature: " + Hex.toHexString(signed));

        // 用公钥验证:
        Signature v = Signature.getInstance("SHA1withRSA");
        v.initVerify(pk);
        v.update(message);
        boolean valid = v.verify(signed);
        System.out.println("valid? " + valid);
    }

}

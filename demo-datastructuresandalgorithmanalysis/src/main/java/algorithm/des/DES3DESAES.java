package algorithm.des;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * https://blog.csdn.net/weixin_42369053/article/details/116889434
 * ==========================DES start==================================
 * DES加密解密，3DES加密解密方法只不过是将DES换成了DESede。 ==========================
 * ==========================DES end==================================
 * <p>
 * ==========================3DES start==================================
 * 3DES，即三重DES，是DES的加强版，也是DES的一个更安全的变形。
 * 它使用3条56位（共168位）的密钥对数据进行三次加密，一般情况下，提供了较为强大的安全性。 实际上，3DES是DES向AES过渡的加密算法。
 * ==========================3DES end==================================
 * <p>
 * ==========================AES start==================================
 * AES在密码学中是高级加密标准（Advanced Encryption Standard）的缩写，该算法是美国联邦政府采用的一种区块加密标准。
 * 这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。最近，高级加密标准已然成为对称密钥加密中最流行的算法之一。
 * AES算法又称Rijndael加密法，该算法为比利时密码学家Joan Daemen和Vincent
 * Rijmen所设计，结合两位作者的名字，以Rijndael命名。AES是美国国家标准技术研究所NIST旨在取代DES的21世纪的加密标准。
 * AES算法将成为美国新的数据加密标准而被广泛应用在各个领域中
 * 。尽管人们对AES还有不同的看法，但总体来说，AES作为新一代的数据加密标准汇聚了强安全性、高性能
 * 、高效率、易用和灵活等优点。AES设计有三个密钥长度：128
 * ，192，256位，相对而言，AES的128密钥比DES的56密钥强得多。AES算法主要包括三个方面：轮变化、圈数和密钥扩展。
 * ==========================AES end==================================
 *
 * @author Liu
 */
public class DES3DESAES {

    //	private static final String algorithm = "DES";
    // KeyGenerator提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator generator;
    // SecretKey负责保存对称密钥
    private SecretKey desKey;
    // Cipher负责完成加密或解密工作
    private Cipher cipher;
    // 该字节数组负责保存加密的结果
    private byte[] cipherByte;

    public DES3DESAES(String algorithm) {
//		Security.addProvider(new SunJCE());
        try {
            // 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
            generator = KeyGenerator.getInstance(algorithm);
            // 生成密钥
            desKey = generator.generateKey();
            // 生成Cipher对象，指定其支持DES算法
            cipher = Cipher.getInstance(algorithm);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public byte[] createEncryptor(String str) {
        try {
            // 根据密钥，对Cipher对象进行初始化,ENCRYPT_MODE表示加密模式
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            byte[] strByte = str.getBytes();
            // 加密，结果保存进cipherByte
            cipherByte = cipher.doFinal(strByte);
        } catch (InvalidKeyException | IllegalBlockSizeException
                 | BadPaddingException e) {
            e.printStackTrace();
        }
        return cipherByte;
    }

    /**
     * 解密
     *
     * @param strByte
     * @return
     */
    public byte[] createDecryptor(byte[] strByte) {
        try {
            // 根据密钥，对Cipher对象进行初始化,ENCRYPT_MODE表示解密模式
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            // 得到明文，存入cipherByte字符数组
            cipherByte = cipher.doFinal(strByte);
        } catch (InvalidKeyException | IllegalBlockSizeException
                 | BadPaddingException e) {
            e.printStackTrace();
        }
        return cipherByte;
    }

    public static void main(String[] args) {

        String str = "liu 测试des加密!done";
        System.out.println("des加密，原文：" + str);
        DES3DESAES des = new DES3DESAES("DES");
        byte[] encryptorStr = des.createEncryptor(str);
        System.out.println("des加密，密文：" + new String(encryptorStr));
        byte[] decryptorStr = des.createDecryptor(encryptorStr);
        System.out.println("des加密，解密后：" + new String(decryptorStr));

        System.out.println();

        DES3DESAES des2 = new DES3DESAES("DESede");
        byte[] encryptorStr2 = des2.createEncryptor(str);
        System.out.println("3des加密，密文：" + new String(encryptorStr2));
        byte[] decryptorStr2 = des2.createDecryptor(encryptorStr2);
        System.out.println("3des加密，解密后：" + new String(decryptorStr2));

        System.out.println();

        DES3DESAES des3 = new DES3DESAES("AES");
        byte[] encryptorStr3 = des3.createEncryptor(str);
        System.out.println("aes加密，密文：" + new String(encryptorStr3));
        byte[] decryptorStr3 = des3.createDecryptor(encryptorStr3);
        System.out.println("aes加密，解密后：" + new String(decryptorStr3));

    }

}

package algorithm.des;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author liangxp
 * @createTime 2021年11月16日 14:47
 */
public class AESUtils {

    static SecretKeySpec secretKeySpec = null;

    static Cipher cipher = null;

    /**
     * AES加密
     *
     * @param data
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(data);
    }


    /**
     * AES解密
     *
     * @param data
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(data);
    }


    private static Cipher getCipher(byte[] key, int model)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (secretKeySpec == null) secretKeySpec = new SecretKeySpec(key, "AES");
        //1 
        if (cipher == null) cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //2 
        //if (cipher == null) cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //2 IvParameterSpec  params = new IvParameterSpec ("2222222222222222".getBytes());
        //3 https://www.jianshu.com/p/a47477e8126a
        //if (cipher == null) cipher = Cipher.getInstance("AES/GCM/NoPadding");
        //GCMParameterSpec params = new GCMParameterSpec(16 * Byte.SIZE, "2222222222222222".getBytes());
        //cipher.init(model, secretKeySpec, params);
        cipher.init(model, secretKeySpec);
        return cipher;
    }


    public static void main(String[] args) throws Exception {

        String data = "{\n" +
                "  \"name\": \"\",\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 10\n" +
                "}";

        String add = "{\n" +
                "  \"nameCh\": \"abc\",\n" +
                "  \"nameEn\": \"abc\",\n" +
                "  \"remark\": \"abc\"\n" +
                "}";

        String encode = Base64.getEncoder().encodeToString(encrypt(add.getBytes(), Constants.AES_KEY.getBytes()));
        System.out.println(encode);

        System.out.println(new String(decrypt(Base64.getDecoder().decode(encode), Constants.AES_KEY.getBytes())));

    }

}

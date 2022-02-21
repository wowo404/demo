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

        String login = "{\n" +
                "  \"password\": \"123456\",\n" +
                "  \"userName\": \"admin\"\n" +
                "}";

        String data = "{\n" +
                "  \"name\": \"\",\n" +
                "  \"pageNum\": 1,\n" +
                "  \"pageSize\": 10\n" +
                "}";

        String edit = "{\n" +
                "  \"drawingNumber\": \"abc\",\n" +
                "  \"en\": \"abcd\",\n" +
                "  \"frc\": \"ddde\",\n" +
                "  \"id\": 1,\n" +
                "  \"tur\": \"aeee\",\n" +
                "  \"zh\": \"发动机\"\n" +
                "}";

        String encode = Base64.getEncoder().encodeToString(encrypt(login.getBytes(), Constants.AES_KEY.getBytes()));
        System.out.println(encode);

        String token = "tizma4ERj4+CByc0AakGQ8V0qWAAlKDrPcYS8vwcd3kB2iftkmfIm9oDCSmSmkw8pQkKX7mC+sHNLE+4874HdC9oe7Abdb69VTd8/Adxv8EUqT1qI4/KcUW+nf16zcrUZDZMMoXTRUuziY09x4ZzQk0SxuM3YZ3+d6+EB8xHumOvcw8/BRB30g4IIuPDfph6wQs3bB58INumyFcIFSKg6MPoDZoAb1sJvPX8Py8TpJlRQsugnzafPkwzDZNJC4vW";

        String infoResp = "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";
        System.out.println(new String(decrypt(Base64.getDecoder().decode(infoResp), Constants.AES_KEY.getBytes())));

    }

}

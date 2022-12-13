package algorithm.des;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AesUtil {
	
	/**
	 * 加密/解密算法/工作模式/填充方式
	 * 
	 * JAVA6 支持PKCS5PADDING填充方式 Bouncy castle支持PKCS7Padding填充方式
	 * */
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
		

	/**
	 * JDK只支持AES-128加密，也就是密钥长度必须是128bit；参数为密钥key，key的长度小于16字符时用"0"补充，
	 * key长度大于16字符时截取前16位
	 **/
	private static SecretKeySpec create128BitsKey(String key) {
		if (key == null) {
			key = "";
		}
		byte[] data = null;
		StringBuffer buffer = new StringBuffer(16);
		buffer.append(key);
		// 小于16后面补0
		while (buffer.length() < 16) {
			buffer.append("0");
		}
		// 大于16，截取前16个字符
		if (buffer.length() > 16) {
			buffer.setLength(16);
		}
		try {
			// System.out.println(buffer.toString());
			data = buffer.toString().getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new SecretKeySpec(data, "AES");
	}

	/**
	 * 创建128位的偏移量，iv的长度小于16时后面补0，大于16，截取前16个字符;
	 *
	 * @param iv
	 * @return
	 */
	private static IvParameterSpec create128BitsIV(String iv) {
		if (iv == null) {
			iv = "";
		}
		byte[] data = null;
		StringBuffer buffer = new StringBuffer(16);
		buffer.append(iv);
		while (buffer.length() < 16) {
			buffer.append("0");
		}
		if (buffer.length() > 16) {
			buffer.setLength(16);
		}
		try {
			// System.out.println(buffer.toString());
			data = buffer.toString().getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new IvParameterSpec(data);
	}

	/*
	 * 加密
	 */
	public static String AESEncode(String encodeRules, String content, String iv) {
		try {
			// 1.生成128位的密钥
			SecretKey key = create128BitsKey(encodeRules);
			// 2.创建128位的偏移量
			IvParameterSpec ivParameterSpec = create128BitsIV(iv);
			// 3.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			// 4.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
			// 5.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
			byte[] byte_encode = content.getBytes("utf-8");
			// 6.根据密码器的初始化方式--加密：将数据加密
			byte[] byte_AES = cipher.doFinal(byte_encode);
			// 7.将加密后的数据转换为Base64字符串
			String AES_encode = Base64.getEncoder().encodeToString(byte_AES);
			
			// 8.将字符串返回
			return AES_encode;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		// 如果有错就返加nulll
		return null;
	}

	/*
	 * 解密 解密过程： 1.同加密1-4步 2.将加密后的字符串反纺成byte[]数组 3.将加密内容解密
	 */
	public static String AESDncode(String encodeRules, String content, String iv) {
		try {
			// 1.生成128位的密钥
			SecretKey key = create128BitsKey(encodeRules);
			// 2.创建128位的偏移量
			IvParameterSpec ivParameterSpec = create128BitsIV(iv);
			// 3.根据指定算法AES自成密码器
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			// 4.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			// 5.将加密并编码后的内容解码成字节数组
			byte[] byte_content = Base64.getDecoder().decode(content);
			// 6. 解密
			byte[] byte_decode = cipher.doFinal(byte_content);
			String AES_decode = new String(byte_decode, "utf-8");
			return AES_decode;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		// 如果有错就返加nulll
		return null;
	}

	public static void main(String[] args) {
		System.out.println(AESEncode("1qaz2wsx<>*&^%", "1qaz2wsx<>*&^%", "1qaz2wsx<>*&^%"));
	}

}

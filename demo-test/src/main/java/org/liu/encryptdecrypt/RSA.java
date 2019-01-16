package org.liu.encryptdecrypt;

/*
 --------------------------------------------**********--------------------------------------------

 该算法于1977年由美国麻省理工学院MIT(Massachusetts Institute of Technology)的Ronal Rivest，Adi Shamir和Len Adleman三位年轻教授提出，并以三人的姓氏Rivest，Shamir和Adlernan命名为RSA算法，是一个支持变长密钥的公共密钥算法，需要加密的文件快的长度也是可变的!

 所谓RSA加密算法，是世界上第一个非对称加密算法，也是数论的第一个实际应用。它的算法如下：

 1.找两个非常大的质数p和q（通常p和q都有155十进制位或都有512十进制位）并计算n=pq，k=(p-1)(q-1)。

 2.将明文编码成整数M，保证M不小于0但是小于n。

 3.任取一个整数e，保证e和k互质，而且e不小于0但是小于k。加密钥匙（称作公钥）是(e, n)。

 4.找到一个整数d，使得ed除以k的余数是1（只要e和n满足上面条件，d肯定存在）。解密钥匙（称作密钥）是(d, n)。

 加密过程： 加密后的编码C等于M的e次方除以n所得的余数。

 解密过程： 解密后的编码N等于C的d次方除以n所得的余数。

 只要e、d和n满足上面给定的条件。M等于N。

 --------------------------------------------**********--------------------------------------------
 */

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.liu.model.ChannelProductReq;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {

	private static final String CHARSET = "UTF-8";
	private static final String ALGORITHM = "RSA";
	private static final int ENCRYPT_SEGMENT_SIZE = 245;
	private static final int DECRYPT_SEGMENT_SIZE = 256;

	/**
	 * 生成公钥、私钥对
	 *
	 * @param keySize
	 * @return
	 */
	public static RSA.KeyPairInfo getKeyPair(int keySize) {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
			// 初始化密钥对生成器，密钥大小一般要大于1024位，
			keyPairGen.initialize(keySize);
			// 生成一个密钥对，保存在keyPair中
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// 得到私钥
			RSAPrivateKey oraprivateKey = (RSAPrivateKey) keyPair.getPrivate();
			// 得到公钥
			RSAPublicKey orapublicKey = (RSAPublicKey) keyPair.getPublic();

			RSA.KeyPairInfo pairInfo = new RSA.KeyPairInfo(keySize);
			// 公钥
			byte[] publicKeybyte = orapublicKey.getEncoded();

			String publicKeyString = Base64.getEncoder().encodeToString(publicKeybyte);
			pairInfo.setPublicKey(publicKeyString);
			// 私钥
			byte[] privateKeybyte = oraprivateKey.getEncoded();
			String privateKeyString = Base64.getEncoder().encodeToString(privateKeybyte);
			pairInfo.setPrivateKey(privateKeyString);

			return pairInfo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取公钥对象
	 *
	 * @param publicKeyBase64
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static PublicKey getPublicKey(String publicKeyBase64) throws Exception {

		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		X509EncodedKeySpec publicpkcs8KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBase64.getBytes(CHARSET)));
		PublicKey publicKey = keyFactory.generatePublic(publicpkcs8KeySpec);
		return publicKey;
	}

	/**
	 * 获取私钥对象
	 *
	 * @param privateKeyBase64
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(String privateKeyBase64) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PKCS8EncodedKeySpec privatekcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBase64.getBytes(CHARSET)));
		PrivateKey privateKey = keyFactory.generatePrivate(privatekcs8KeySpec);
		return privateKey;
	}

	/**
	 * 使用共钥加密
	 *
	 * @param content
	 *            待加密内容
	 * @param publicKeyBase64
	 *            公钥 base64 编码
	 * @return 经过 base64 编码后的字符串
	 */
	public static String encrypt(String content, String publicKeyBase64) {
		return encrypt(content, publicKeyBase64, ENCRYPT_SEGMENT_SIZE);
	}

	/**
	 * 使用共钥加密（分段加密）
	 *
	 * @param content
	 *            待加密内容
	 * @param publicKeyBase64
	 *            公钥 base64 编码
	 * @param segmentSize 分段大小,一般小于
	 *            keySize/8（段小于等于0时，将不使用分段加密）
	 * @return 经过 base64 编码后的字符串
	 */
	public static String encrypt(String content, String publicKeyBase64, int segmentSize) {
		try {
			PublicKey publicKey = getPublicKey(publicKeyBase64);
			return encrypt(content, publicKey, segmentSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 分段加密
	 *
	 * @param ciphertext
	 *            密文
	 * @param key
	 *            加密秘钥
	 * @param segmentSize
	 *            分段大小，<=0 不分段
	 * @return
	 */
	public static String encrypt(String ciphertext, Key key, int segmentSize) {
		try {
			// 用公钥加密
			byte[] srcBytes = ciphertext.getBytes(CHARSET);

			// Cipher负责完成加密或解密工作，基于RSA
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			// 根据公钥，对Cipher对象进行初始化
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] resultBytes = null;

			if (segmentSize > 0)
				resultBytes = cipherDoFinal(cipher, srcBytes, segmentSize); // 分段加密
			else
				resultBytes = cipher.doFinal(srcBytes);

			String base64Str = Base64.getEncoder().encodeToString(resultBytes);
			return base64Str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 分段大小
	 */
	private static byte[] cipherDoFinal(Cipher cipher, byte[] srcBytes, int segmentSize)
			throws IllegalBlockSizeException, BadPaddingException, IOException {
		if (segmentSize <= 0)
			throw new RuntimeException("分段大小必须大于0");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int inputLen = srcBytes.length;
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > segmentSize) {
				cache = cipher.doFinal(srcBytes, offSet, segmentSize);
			} else {
				cache = cipher.doFinal(srcBytes, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * segmentSize;
		}
		byte[] data = out.toByteArray();
		out.close();
		return data;
	}

	public static void main(String[] args) {
		//钱粒那边的私钥
		String contentBase64 = "e9jxoJ04/iKsTU6+CSvr7gjVU/VDT30HJBhgYhLZPpenPuGW9fJeUxcrC+vuXiCffSsRabWL/Mx0xwA5IZ+BM2Vxag/HZ02xn7hhvk8JHBiqbDaQV3fJi8aZiAOYgouVLir3LSaUsOb9+Qv8UqZqV6j6DRd24cLCLxx+DBV/763/5HOy9GA686btg3JxitdQQUWuQDfZbFfdvbQdovxFmkGaItthieCtO3okr0K227xh48kKmP8Qag/wV1P+5fIzbfUYh37S6YLOC6Gf7lhr/oFQUrOyGSwpWQrAlKZwImBul27qdEv0xizJ/b9b/t/lbfFpdQ17790vJxx2gqKpM1QulWWz7a7QSNN7fz58qNaej500LA2j6ZTslHROpH3FVqb4gItJ5txSWyVNkF+oV5X3M4zbZOmnXPw3ZKw23dTyb928zRrgwM3yC1/sHtl1JON14eA0YtOvVAPTRsbsIzxsgf1A7POgMlFKfZZKf5Kyq8jCE5tddYvBHc01rUpUP8gfxvfL5yjbaOcV4nGr1vdZ8K5ENaAxm3AZaFB/YhWDHYb2LhKwxrZhbamohLSo6NaQ+r1QZtH3c3wo+YwxtEfC92qbJGnWy3GLwKWVko2aNdcujDifWmfGDZXT6nMam3S/kcyJUjNMK6ThzKqfIO8IQq5fAm9Qw6Dh7s+tpIU=";
		String privateKeyBase64 = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCh/iuozoLyiJHXLfXN63VhasJgVvaGzTeuLbm5C+uqYP2n326IKfJMn8XRgnTrOwEUzO6Po4YVF3GZqBYSkXEcp9fs7tyuuG3WI9EdXqh2LY4viAgyG1L7cdWekHyJMNtdGxyYsqs6itWMWJXQ5ycjZ0oVz3YFWrMX3jcWkENANdGIwnz2DbMWMzqkyUjBRvLDqksBXYSRuWjoCG5cJQYWXoexWWKnCwD3y6lQ1zDshBR7ccermX8ktyLCQjv6rOh2ElgaJcESVp2rE17hBXiGeB4xM9xxnKLipaL2xeaos6l0ANeWSxIAuWiEDKK0TLdIaquPkvRHSPXW4mMObnoNAgMBAAECggEAYqBaOouJBFsL2CO+XSH3NFmIgJlRvR7iwKRMSBKu5+vnY7bQGAzZ3bcE/Jrdzn39GYnv0PoxX1QHsRK6ZZGxeU3jU/jV2XamQNNLtQL1tAXmZKAAxWkHa9mRwtmbSPe7BwPDtGBLifgPLkSc6jRiCtcPLxpBc98V3PlJNu6cU1G4waAw0lN0xrD/sjojhy24iMKHmJxFyGsKGN9SvUsL7CuW3LTDErsHSBQOADHMAjD8vdVe07ALPqK876sLlXzPlIuNWo2ijR2DkZSvnN/ijcH1spwsvyPAAS7aQGm+1MFBFbmkDOJfuW/n+k/tCr0ic0+ZgaCCKcLT/I95KHfMoQKBgQDVjLVn6S2QnOfZDjdhHRd4sf8bbYc8BCWJNWpvvzg8JlNwsQKqau+V4imebzK+FTitM9satwZjwsylybqBruUD9b80jFq13Gag9n5Pni1trqy62dJ70S7bgR/BAhs4A0Io3pxtajEpGZF2NGHyl9HHVTFJcZa5xlJMi6trCRggZQKBgQDCMcwZbdSccFBMuOcBs1t/fmeUVoV1xi3+JebCcoRyLGg0C0sGYipauSvs+A+N0eErc+VbEA3/RSj87S/hP/QuU5YDIj5rfS3QBKg/jmaJShVq/4w9WaOlR+5S7mX/qAdGjVRJYj3515MBceIcyRrQOiUcOj2e2lFJhyR65hRUiQKBgAoRklQN6HoTC9NuGipYBs1IBzEx3thzkZmAjT4rk+kyHWkIz4lddWJpwXCY6ZmQwpVYBpKh4ok9nAyNEGAg8OdY5OUDYRuGAsjAFoCGgrnTLI26y3PPDxiGZArjoIjpjmsC4/k8soivje421g9sHRX67FH8KxEjSqoVQ8XOLfcJAoGAFiV7s0K1RooAfl9v6N+9hZKy2VXsrnvFbwB/iploxWSPFbBcBARZ9DK98a5wh6nx1fTHqVTxY7VNcVrTZQrYHBhoOT9EgxmpidmeHnFs/Cc4vhUatzxrz0OeyFJry3YsLj2b6SqNCtss5rNIKCQ/LJQU+10g/ebD8nC5FlL66pECgYAuJMXfAe1zAnOmqxAQT44O9CcsTizBT4qTDaJq8zdogcPU5oAL+vvLRYxlPRqfTYvmMohmi34XaMF71hGVqwAACQW09umjXLok3uzK8B0Vlj0HD4N2lEIc4ws2RFeHUpXidFIQjtxHeW4LpoglyixDUNW5I6GfaWy+Yw4pcaZzCg==";
		String decContent = decrypt(contentBase64, privateKeyBase64);
		System.out.println(decContent);
		//铜牛这边的公钥加密
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnvjRgZGTp5iFWjMeah9UnAAIUPFQJS0/ung6Hd3hs8dR54EA2hAWUJPgj2uDYSqzQrjpqY96UxlnibLALwWdUVIvYJD2MjJocZ8b3shGE8ZZNtaK/6c4v52Ql9svjVG+s3hTasUWmONgupyTgUExreRGfbwhxZwdrwkKofT17W17XQyxOSssDc9FiKHp4HZmpv1Suc8uDXkgexIXd4A+xxniGcX/iPvu1MF0JWntaPQ/XBLoo8Xys1nnfW1t5eL1IeBhJEWD8JJD2BFTC16i43/co4Cc0b3PPWNlsTgn6RFklevNkRLpRcrre9f9WxX/0IyTl9/fub8jfbpT/5VJeQIDAQAB";
		ChannelProductReq req = new ChannelProductReq();
		req.setPhone("15058121294");
		req.setUserId("a1234");
		String encStr = encrypt(JSON.toJSONString(req), publicKey);
		System.out.println(encStr);
		//铜牛这边的私钥解密
		String content = "IVjosN/mZq8HHVfmVqPP/bhIzPRtqVDrGxK1VkZi7erTqsZj9gdLKwOilKDhRWWjqLFawIYUaVhLmRV65dL7Dw0GN1N/6DkVc43shtQFsOGn8Uj4TN3OxohE9xrjRWu+pvyAFUhDjjmBRlJxNUSbxEOr4kbwXsmxYbzWmrPSf3e8e4U1nwpm5Ly/8rca6Yr5kGHf4ZUJYDl9iaDFcljJQ1CObAjkYA+n6Y7xiSJRL+O73l+x54S7CFs+pB6JWJiaReoQHNM47Y7gqmjGigqbgO7a2/P5C+ZgOyoz+0ovDKLYfvhDhmAwRNTQalunEI8Z5zQ02mpb1TKmfwQ2s5mqxg==";
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe+NGBkZOnmIVaMx5qH1ScAAhQ8VAlLT+6eDod3eGzx1HngQDaEBZQk+CPa4NhKrNCuOmpj3pTGWeJssAvBZ1RUi9gkPYyMmhxnxveyEYTxlk21or/pzi/nZCX2y+NUb6zeFNqxRaY42C6nJOBQTGt5EZ9vCHFnB2vCQqh9PXtbXtdDLE5KywNz0WIoengdmam/VK5zy4NeSB7Ehd3gD7HGeIZxf+I++7UwXQlae1o9D9cEuijxfKzWed9bW3l4vUh4GEkRYPwkkPYEVMLXqLjf9yjgJzRvc89Y2WxOCfpEWSV682REulFyut71/1bFf/QjJOX39+5vyN9ulP/lUl5AgMBAAECggEAb57Fe/R7eHHfMDY0ZM8a2W4saKkHXRMobAV+yPnCQDjziVlK/9HL2Uf7pLgYx2Dz/SVY63WSrkZoLIcV6e5HR5ItMeCW/Sfnrd/J8eMtUTUz7w01C8yeS+yWWgnMma2L+PfDmnRIKZF9mYnXCCp+d1qRJDe0fsvfC8eNb6pQAyR5ccCQH22NvXovqn/7fqOabbsyg2mjiyoaQlI6rHtp19gTwsiimukrGbXXKOy3DozIPzhogxoPNbOtr7q0VtgJ2K8acL2Za8HaOAPGili2ZFnGxKA86C6n5IWpWFgpiapRck85+o/NTDayS5KSRkA/lf+2H1DDTTJhdNrHk0JnAQKBgQD9wNHslO5ww/OzoEHvrKpOi4It6PLm5nrR9024K31kklxgfRsHHFHIN9W6vKDCGeEGWyRb711WuDZBBZry9pd29/zdrexZ/jDuxDidFDDyFmdzDPHvB12xtq679t5F7urT1qfXkiGgRZQdXhhDAK7elX4+LnCF/ynWyGW2gkz7wwKBgQCgYSiZYas2PhBeydh3iiT3nEA3VpQjnA7VNeaR3PqJ/NRoLtixX2zgdID+T9c7tqoPopMuBTe6g32IuArkOlpD2z2qc128aMsLw+yoW2klmhcau16ahFjnp+vGC4WqmegbF2JVuAi9oSBh3UbcZVZc7F9yFEz6JAgLVTfLhQ9eEwKBgEc0UjPG3tm5wqrG73mnw+pib1cepPpx9kjw0lg5q+gKwe05bdoi8jfyowDRaXLN/DziWmOJ2XE6I88/GWnQFFSH5eb2pPnjwCtf2FsLjWiBLOo12bWvJXV2w7Vu4pRRJIfpFhQBp3nnzFYpTzmN+YPwAwrD0UNohH8GoLQEAEItAoGAS1N22xt5a7+Ytrx81CBpHncf3h3m9BavDMsFIUs1zSuXo9sk4aIdJAr0Lv3W7G/tzewgzoOa2ho6EsXGL82MacSGYRFQXFanzz3VYHmWmBeYdHC+jTpN+3fuEBWHUUp6Loc8I2CNkmskJ3Ljfk6GUpyec5k/UlksAg6jZw522ksCgYEA3x8tj5VWiqGgQ+bNO6eIiwANacA2+JrzDhPWuxqes1HCwTTbbmxifzDX+CJHrS8qtZmuRKeBQND/SDVUNwRXS9tDnNiEYCAdMZpd5wReRui3Q8EdDYxKDz+6htZ3L48qRUCdzZalTRdq6eVDWKHSJzycfpoOaggyYIu5jPkKlPw=";
		String tnContent = decrypt(content, privateKey);
		System.out.println(tnContent);
	}
	
	/**
	 * 使用私钥解密
	 *
	 * @param contentBase64
	 *            待加密内容,base64 编码
	 * @param privateKeyBase64
	 *            私钥 base64 编码
	 * @return
	 */
	public static String decrypt(String contentBase64, String privateKeyBase64) {
		return decrypt(contentBase64, privateKeyBase64, DECRYPT_SEGMENT_SIZE);
	}

	/**
	 * 使用私钥解密（分段解密）
	 *
	 * @param contentBase64
	 *            待加密内容,base64 编码
	 * @param privateKeyBase64
	 *            私钥 base64 编码
	 * @segmentSize 分段大小
	 * @return
	 */
	public static String decrypt(String contentBase64, String privateKeyBase64, int segmentSize) {
		try {
			PrivateKey privateKey = getPrivateKey(privateKeyBase64);
			return decrypt(contentBase64, privateKey, segmentSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 分段解密
	 *
	 * @param contentBase64
	 *            密文
	 * @param key
	 *            解密秘钥
	 * @param segmentSize
	 *            分段大小（小于等于0不分段）
	 * @return
	 */
	public static String decrypt(String contentBase64, Key key, int segmentSize) {
		try {
			// 用私钥解密
			byte[] srcBytes = Base64.getDecoder().decode(contentBase64);
			// Cipher负责完成加密或解密工作，基于RSA
			Cipher deCipher = Cipher.getInstance(ALGORITHM);
			// 根据公钥，对Cipher对象进行初始化
			deCipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decBytes = null;// deCipher.doFinal(srcBytes);
			if (segmentSize > 0)
				decBytes = cipherDoFinal(deCipher, srcBytes, segmentSize); // 分段加密
			else
				decBytes = deCipher.doFinal(srcBytes);

			String decrytStr = new String(decBytes);
			return decrytStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 秘钥对
	 *
	 */
	public static class KeyPairInfo {
		public KeyPairInfo(int keySize) {
			setKeySize(keySize);
		}

		public KeyPairInfo(String publicKey, String privateKey) {
			setPrivateKey(privateKey);
			setPublicKey(publicKey);
		}

		String privateKey;
		String publicKey;
		int keySize = 0;

		public String getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(String privateKey) {
			this.privateKey = privateKey;
		}

		public String getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(String publicKey) {
			this.publicKey = publicKey;
		}

		public int getKeySize() {
			return keySize;
		}

		public void setKeySize(int keySize) {
			this.keySize = keySize;
		}
	}

}
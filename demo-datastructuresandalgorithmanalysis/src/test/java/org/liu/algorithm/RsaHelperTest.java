package org.liu.algorithm;

import algorithm.des.RsaHelper;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertTrue;

/** 
*
* @author xxj 
*/
public class RsaHelperTest {
	
	/**
	 * 获取秘钥对
	 * 
	 */
	@Test
	public void getKeyPairTest() {
		RsaHelper rsa = new RsaHelper();
		RsaHelper.KeyPairInfo info = rsa.getKeyPair(1024);
		assertTrue(info!=null);
		System.out.println(String.format("公钥：%s", info.getPublicKey()));
		System.out.println(String.format("私钥：%s", info.getPrivateKey()));
	}
	/**
	 * rsa 不分段加密，使用莫值为1024 时y，明文不能超过 117
	 * 
	 */
	@Test
	public void encipherTest() {
		RsaHelper rsa = new RsaHelper();
		RsaHelper.KeyPairInfo info = rsa.getKeyPair(1024); //每次调用的公钥 私钥都一样
		assertTrue(info!=null);
		System.out.println(String.format("公钥：%s", info.getPublicKey()));
		System.out.println(String.format("私钥：%s", info.getPrivateKey()));
		
		String content="rsa加密、解密测试";
		System.out.println(String.format("明文：%s", content));
		
		String ciphertext = rsa.encipher(content, info.getPublicKey());
		System.out.println(String.format("密文：%s", ciphertext));
		
		String deTxt = rsa.decipher(ciphertext, info.getPrivateKey());
		System.out.println(String.format("解密：%s", deTxt));
		
		assertTrue(content.equals(deTxt));
	}
	/**
	 * rsa 分段机密测试（长内容加密）
	 * 
	 */
	@Test
	public void encipherSegmentTest() {
		int keySize=2048;
		RsaHelper rsa = new RsaHelper();
		RsaHelper.KeyPairInfo info = rsa.getKeyPair(keySize); //每次调用的公钥 私钥都一样
		assertTrue(info!=null);
		System.out.println(String.format("公钥：%s", info.getPublicKey()));
		System.out.println(String.format("私钥：%s", info.getPrivateKey()));
		
		String content="1.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "2.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "3.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "4.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "5.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "6.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "7.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "8.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "9.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n"
				+ "0.rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;rsa加密、解密测试;\r\n";
		System.out.println(String.format("明文：%s", content));
		
		int enSegmentSize=245;//keysize=1024时，分段不能大于117 ；keysize>=2048时，分段不能大于keySize/8+128；
		String ciphertext = rsa.encipher(content, info.getPublicKey(),enSegmentSize);
		System.out.println(String.format("密文：%s", ciphertext));

		int deSegmentSize=256;//等于keySize/8
		String deTxt = rsa.decipher(ciphertext, info.getPrivateKey(),deSegmentSize);
		System.out.println(String.format("解密：%s", deTxt));
		
		assertTrue(content.equals(deTxt));
	}
	/**
	 * 测试公钥加密、私钥解密
	 * 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	@Test
	public void encipherUsePublic() 
			throws InvalidKeySpecException, NoSuchAlgorithmException{
		RsaHelper rsa = new RsaHelper();
		RsaHelper.KeyPairInfo info = rsa.getKeyPair(1024); //每次调用的公钥 私钥都一样
		assertTrue(info!=null);
		System.out.println(String.format("公钥：%s", info.getPublicKey()));
		System.out.println(String.format("私钥：%s", info.getPrivateKey()));
		
		String content="rsa加密、解密测试";
		System.out.println(String.format("明文：%s", content));
		
		PublicKey pubKey = rsa.getPublicKey(info.getPublicKey());
		String ciphertext = rsa.encipher(content, pubKey,0);
		System.out.println(String.format("密文：%s", ciphertext));

		PrivateKey privKey = rsa.getPrivateKey(info.getPrivateKey());
		String deTxt = rsa.decipher(ciphertext,privKey,0);
		System.out.println(String.format("解密：%s", deTxt));
		
		assertTrue(content.equals(deTxt));
	}
	/**
	 * 测试公钥加密、私钥解密
	 * 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	@Test
	public void encipherUsePrivateKey() 
			throws InvalidKeySpecException, NoSuchAlgorithmException{
		RsaHelper rsa = new RsaHelper();
		RsaHelper.KeyPairInfo info = rsa.getKeyPair(1024); //每次调用的公钥 私钥都一样
		assertTrue(info!=null);
		System.out.println(String.format("公钥：%s", info.getPublicKey()));
		System.out.println(String.format("私钥：%s", info.getPrivateKey()));
		
		String content="rsa加密、解密测试";
		System.out.println(String.format("明文：%s", content));

		PrivateKey privKey = rsa.getPrivateKey(info.getPrivateKey());
		String ciphertext = rsa.encipher(content, privKey,0);
		System.out.println(String.format("密文：%s", ciphertext));

		PublicKey pubKey = rsa.getPublicKey(info.getPublicKey());
		String deTxt = rsa.decipher(ciphertext,pubKey,0);
		System.out.println(String.format("解密：%s", deTxt));
		
		assertTrue(content.equals(deTxt));
	}
}

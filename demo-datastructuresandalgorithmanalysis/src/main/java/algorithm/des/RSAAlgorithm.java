package algorithm.des;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAAlgorithm {
	
	private KeyPairGenerator keyGen;
	
	private KeyPair keyPair;
	
	private RSAPublicKey publicKey;
	
	private RSAPrivateKey privateKey;
	
	private Cipher cipher;
	
	public RSAAlgorithm(){
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
			keyPair = keyGen.generateKeyPair();
			publicKey = (RSAPublicKey) keyPair.getPublic();
			System.out.println(publicKey.toString());
			privateKey = (RSAPrivateKey) keyPair.getPrivate();
			System.out.println(privateKey.toString());
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用公钥加密
	 */
	public byte[] encrypt(byte[] srcBytes){
		if (publicKey != null){
			try {
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				byte[] resultBytes = cipher.doFinal(srcBytes);
				return resultBytes;
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 用私钥解密
	 */
	public byte[] decrypt(byte[] encBytes){
		if(privateKey != null){
			try {
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				byte[] resultBytes = cipher.doFinal(encBytes);
				return resultBytes;
			} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		
		String str = "i will come back!";
		System.out.println(str);
		RSAAlgorithm rsa = new RSAAlgorithm();
		byte[] encBytes = rsa.encrypt(str.getBytes());
		System.out.println(new String(encBytes));
		byte[] decBytes = rsa.decrypt(encBytes);
		System.out.println(new String(decBytes));

	}

}

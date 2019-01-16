package org.liu.algorithm;

import java.util.UUID;

public class RC4 {

	public static String rc4(String aInput, String aKey) {
		int[] iS = new int[256];
		byte[] iK = new byte[256];

		for (int i = 0; i < 256; i++)
			iS[i] = i;

		int j = 1;

		for (short i = 0; i < 256; i++) {
			iK[i] = (byte) aKey.charAt((i % aKey.length()));
		}

		j = 0;

		for (int i = 0; i < 255; i++) {
			j = (j + iS[i] + iK[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
		}

		int i = 0;
		j = 0;
		char[] iInputChar = aInput.toCharArray();
		char[] iOutputChar = new char[iInputChar.length];
		for (short x = 0; x < iInputChar.length; x++) {
			i = (i + 1) % 256;
			j = (j + iS[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
			int t = (iS[i] + (iS[j] % 256)) % 256;
			int iY = iS[t];
			char iCY = (char) iY;
			iOutputChar[x] = (char) (iInputChar[x] ^ iCY);
		}

		return charToHexString(iOutputChar);
	}
	
	public static String charToHexString(char[] s){
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			int ch = (int)s[i];
			result.append(Integer.toHexString(ch));
		}
		
		return result.toString();
	}

	/**
	 * 字符串转换为16进制字符串
	 */
	public static String stringToHexString(String s) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			result.append(s4);
		}
		return result.toString();
	}

	/**
	 * @Description:字节数组转16进制字符串
	 */
	public static String bytes2HexString(byte[] b) {
		StringBuffer result = new StringBuffer();
		String hex;
		for (int i = 0; i < b.length; i++) {
			hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			result.append(hex.toUpperCase());
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(rc4("15058124996", UUID.randomUUID().toString()));
	}

}

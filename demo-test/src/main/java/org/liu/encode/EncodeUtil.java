package org.liu.encode;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * TODO:本功能还无效
 * @author liuzhangsheng
 */
public class EncodeUtil {

	public static void main(String[] args) {
		System.out.println(Charset.defaultCharset());
		String[] arr = getEncoding("12132");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public static String[] getEncoding(String str) {
        String[] encodeList = {"GB2312", "ISO-8859-1", "UTF-8", "GBK", "GB18030", "Big5", "UTF-16LE", "Shift_JIS", "EUC-JP", "ISO-2022-JP"};
        String[] arr = new String[10];
        for (int i = 0; i < encodeList.length; i++) {
            try {
                if (str.equals(new String(str.getBytes(encodeList[i]), encodeList[i]))) {
                    arr[i] = encodeList[i];
//                    return encodeList[i];
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }

}

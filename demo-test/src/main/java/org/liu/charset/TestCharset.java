package org.liu.charset;

import java.io.UnsupportedEncodingException;

public class TestCharset {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String fileName = new String("信息总览.xlsx".getBytes("gb2312"),"iso-8859-1");
		System.out.println(fileName);
		
	}

}

package org.liu.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class FileReader {

	public static final String SRC = "src/main/resources/pdf/contract_tpl.pdf";
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader();
//		fr.read();
		fr.bufferRead("src/main/resources/bank");
//		String fullPath = FilenameUtils.getFullPath("C://A/B/C/A.txt");
//		System.out.println(fullPath);
//		System.out.println(new File(fullPath).exists());
	}
	
	public void read() throws IOException{
		InputStream is = new FileInputStream(new File(SRC));
		byte[] buff = IOUtils.toByteArray(is, is.available());
		String content = new String(buff, "UTF-8");
		System.out.println(content);
	}
	
	public void bufferRead(String path) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.FileReader(path + "/P0046000/sm2PublicKey.key"));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		System.out.println(sb.toString());
	}

}

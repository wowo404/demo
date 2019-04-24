package org.liu.filereader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.io.*;

public class FileReader {

	public static final String SRC = "src/main/resources/pdf/contract_tpl.pdf";
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader();
//		fr.saveFile();
//		fr.bufferRead("src/main/resources/bank");
//		String fullPath = FilenameUtils.getFullPath("C://A/B/C/A.txt");
//		System.out.println(fullPath);
//		System.out.println(new File(fullPath).exists());

        File file = new File(SRC);
        String name = file.getName();
        String[] split = name.split("\\.");
        for (String s : split) {
            System.out.println(s);
        }

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

	public void fileName(){
        File file = new File(SRC);
        String name = file.getName().substring(0, file.getName().lastIndexOf("."));
        System.out.println(name);
    }

    public void saveFile() throws IOException {
	    //target
//        FileOutputStream fos = new FileOutputStream(new File("/Users/liuzhangsheng/Downloads/e.txt"));
        //source
        InputStream is = new FileInputStream(new File("/Users/liuzhangsheng/Downloads/a.txt"));
        FileUtils.copyInputStreamToFile(is, new File("/Users/liuzhangsheng/Downloads/f.txt"));
//        byte[] buffer = IOUtils.toByteArray(is, is.available());
//        fos.write(buffer);
        is.close();
//        fos.close();
    }

}

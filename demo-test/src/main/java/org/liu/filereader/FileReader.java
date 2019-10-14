package org.liu.filereader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.Arrays;

public class FileReader {

	public static final String SRC = "src/main/resources/pdf/contract_tpl.pdf";
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader();
//		fr.filter();
//		fr.read();
//		fr.read2();
//		fr.saveFile();
		String content = fr.bufferRead("D:\\home\\manufacture\\file\\2019\\09\\24\\00000145-7-20190924000001.txt");
		content = content.replaceFirst("," ,"");
		String[] split = content.split(",");
		String[] a = new String[split.length];
		for (int i = 0; i < split.length; i++) {
			double temp = Double.parseDouble(split[i]) / 10 + RandomUtils.nextDouble(0.1, 2.0);
			a[i] = String.valueOf(temp == 0 ? 1 : temp);
		}
		String path = "D:\\home\\manufacture\\file\\2019\\09\\24\\00000145-7-20190924000001-test.txt";
		File file = new File(path);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		writer.write(Arrays.toString(a));
//		String fullPath = FilenameUtils.getFullPath("C://A/B/C/A.txt");
//		System.out.println(fullPath);
//		System.out.println(new File(fullPath).exists());

    }

    public void filter() throws IOException {
		String path = "D:\\projects\\manufacture\\";
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		String fileName = "manufacture.properties";
		File file = new File(path + File.separator + fileName);
		if (!file.exists()) {
			boolean newFile = file.createNewFile();
			System.out.println(newFile);
		}
		FilenameFilter filter = (dir1, name) -> {
			if (name.startsWith("manufacture")){
				return true;
			}
			return false;
		};
		File[] files = dir.listFiles(filter);
		for (File file1 : files) {
			System.out.println(file1.getName());
		}
	}
	
	public void read() throws IOException{
		InputStream is = new FileInputStream(new File("D:\\work\\workspace-idea\\demo\\demo-test\\src\\main\\resources\\test.el"));
		byte[] buff = IOUtils.toByteArray(is, is.available());
		String content = new String(buff, "UTF-8");
		System.out.println(content);
	}

	public void read2() throws IOException{
		InputStream is = new FileInputStream(new File("D:\\work\\workspace-idea\\demo\\demo-test\\src\\main\\resources\\test.el"));
		byte[] buff = new byte[is.available()];
		int offset = 0;
		int read;
		while ((read = is.read(buff, offset, 512)) != -1){
			offset += read;
		}
		String content = new String(buff, "UTF-8");
		System.out.println(content);
	}
	
	public static String bufferRead(String path) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.FileReader(path));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
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

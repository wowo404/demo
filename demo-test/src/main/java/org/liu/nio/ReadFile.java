package org.liu.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

	public static void main(String[] args) throws IOException {
		readFile();
	}
	
	public static void readFile() throws IOException {
		
		FileInputStream fis = new FileInputStream("/Users/liuzhangsheng/Documents/workspace/demo/src/main/resources/xml/table_css.html");
		FileChannel fc = fis.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//buffer.clear();//重设缓冲区，使它可以接受读入的数据
		fc.read(buffer);
		
		buffer.flip();
		
		char[] charArr = new char[buffer.limit()];
		int i = 0;
		while(buffer.hasRemaining()) {
			byte b = buffer.get();
			charArr[i] = (char)b;
			i++;
		}
		System.out.println(charArr);
		
		fis.close();
		
	}
	
	public static void writeFile() throws IOException {
		String[] message = {"a", "1", ","};
		FileOutputStream fos = new FileOutputStream("/Users/liuzhangsheng/Documents/workspace/demo/src/main/resources/xml/cope.html");
		FileChannel fc1 = fos.getChannel();
		ByteBuffer buffer1 = ByteBuffer.allocate(1024);
		buffer1.clear();
		for (String msg : message) {
			buffer1.put(msg.getBytes());
		}
		buffer1.flip();//让缓冲区可以将新读入的数据写入另一个通道。
		fc1.write(buffer1);
		fos.close();
	}
	
	public static void copyFile() throws IOException {
		//read
		FileInputStream fis = new FileInputStream("/Users/liuzhangsheng/Documents/workspace/demo/src/main/resources/xml/table_css.html");
		FileChannel fcin = fis.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();//重设缓冲区，使它可以接受读入的数据
		int i = fcin.read(buffer);
		if(i == -1) {
			
		}
		fis.close();
		
		//write
		FileOutputStream fos = new FileOutputStream("/Users/liuzhangsheng/Documents/workspace/demo/src/main/resources/xml/cope.html");
		FileChannel fcout = fos.getChannel();
		buffer.flip();//让缓冲区可以将新读入的数据写入另一个通道。
		fcout.write(buffer);
		fos.close();
		
		
	}

}

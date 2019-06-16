package org.liu.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipDecompressing {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(
					"ziptest.zip"));//输入源zip路径
			BufferedInputStream bin = new BufferedInputStream(zin);
			String parent = "ziptest"; //输出路径（文件夹目录）
			File fout = null;
			ZipEntry entry;
			try {
				while ((entry = zin.getNextEntry()) != null
						&& !entry.isDirectory()) {
					fout = new File(parent, entry.getName());
					if (!fout.exists()) {
						(new File(fout.getParent())).mkdirs();
					}
					FileOutputStream out = new FileOutputStream(fout);
					BufferedOutputStream bout = new BufferedOutputStream(out);
					int b;
					while ((b = bin.read()) != -1) {
						bout.write(b);
					}
					bout.close();
					out.close();
					System.out.println(fout + "解压成功");
				}
				bin.close();
				zin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗费时间： " + (endTime - startTime) + " ms");
	}

}

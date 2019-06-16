/*
 * Project: POI
 * 
 * File Created at 2014年3月30日 下午10:52:27
 * 
 * Copyright 2012 seaway.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Seaway Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with seaway.com.
 */
package cn.seaway.word2007;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

public class TestZipFile {

    /*
     * inputFileName 输入一个文件夹
     * zipFileName 输出一个压缩文件夹
     */
     public void zip(String inputFileName) throws Exception {
         String zipFileName = "D:\\contractPDF\\zipFiletest.docx"; //打包后文件名字
         System.out.println(zipFileName);
         zip(zipFileName, new File(inputFileName));
     }

     private void zip(String zipFileName, File inputFile) throws Exception {
         ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
         zip(out, inputFile, "");
         System.out.println("zip done");
         out.close();
     }

     private void zip(ZipOutputStream out, File f, String base) throws Exception {
         if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
            zip(out, fl[i], base + fl[i].getName());
          }
         }else {
            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            System.out.println(base);
            while ( (b = in.read()) != -1) {
             out.write(b);
          }
          in.close();
        }
     }

     public static void main(String [] temp){
         TestZipFile book = new TestZipFile();
         try {
            book.zip("D:\\contractPDF\\zipFile");//你要压缩的文件夹
         }catch (Exception ex) {
            ex.printStackTrace();
        }
     }

}

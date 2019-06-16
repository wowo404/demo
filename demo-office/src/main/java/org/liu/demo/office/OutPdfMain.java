/*
 * Project: my-app-simple
 * 
 * File Created at 2014年3月4日 上午11:44:25
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
package org.liu.demo.office;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 直接导出pdf
 * <br>----------------------------------------------------变更记录--------------------------------------------------
 * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
 * <br> 0     | 2014年3月4日 上午11:44:25  	|  刘章盛     | 创建  
 */
public class OutPdfMain {

    /** 
     * 直接导出pdf
     * @param args
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月4日 上午11:44:25  	|  刘章盛     | 创建  
     */
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("D:\\two.pdf"));
            doc.open();
            doc.addTitle("借款协议");
            doc.add(new Paragraph("Hello world"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }

    }

}

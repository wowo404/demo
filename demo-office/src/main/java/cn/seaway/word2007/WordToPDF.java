/*
 * Project: POI
 * 
 * File Created at 2014年3月10日 上午11:20:11
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * word to pdf
 * <br>----------------------------------------------------变更记录--------------------------------------------------
 * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
 * <br> 0     | 2014年3月10日 上午11:20:11  	|  刘章盛     | 创建  
 */
public class WordToPDF {

    /** 
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为 
     * http://www.openoffice.org/ 
     *  
     * <pre> 
     * 方法示例: 
     * String sourcePath = "F:\\office\\source.doc"; 
     * String destFile = "F:\\pdf\\dest.pdf"; 
     * Converter.office2PDF(sourcePath, destFile); 
     * </pre> 
     *  
     * @param sourceFile 
     *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc, 
     *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc 
     * @param destFile 
     *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf 
     * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0, 
     *         则表示操作成功; 返回1, 则表示转换失败 
     */
    public static int office2PDF(String sourceFile, String destFile) {
        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return -1;// 找不到源文件, 则返回-1
            }

            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            // 启动OpenOffice的服务
            String command = getOpenOffficeCommand();
            Process pro = Runtime.getRuntime().exec(command);
            // connect to an OpenOffice.org instance running on port 8100
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            String name = "Microsoft Word 2007 XML";
            DocumentFamily family = DocumentFamily.TEXT;
            String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            String extension = "docx";
            converter.convert(inputFile, new DocumentFormat(name, family, mimeType, extension), outputFile, null);

            // close the connection
            connection.disconnect();
            // 关闭OpenOffice服务的进程
            pro.destroy();
            
            System.out.println("word to pdf success");
            
            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("word to pdf failure,FileNotFoundException");
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("word to pdf failure,IOException");
        }

        return 1;
    }
    
    /**
     * 
     * 根据服务器部署机器操作系统来获取配置的openOffice的服务的命令
     * @return
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                          |   作者      |                          描述                                                         
     * <br> 0     | 2014年3月21日 上午10:35:02   |  刘章盛     | 创建
     */
    private static String getOpenOffficeCommand() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            String officeHome = "\\usr\\lib\\openoffice.org3\\program\\";
            String command = "soffice -headless -accept=socket,host=127.0.0.1,port=8100;urp; -nofirststartwizard &";
            return officeHome + command;
        } else if (Pattern.matches("Windows.*", osName)) {
            String officeHome = "C:\\Program Files (x86)\\OpenOffice 4\\program\\";
            String command = "soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
            return officeHome + command;
        } else if (Pattern.matches("Mac.*", osName)) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        String sourceFile = "D:\\contractPDF\\20140317\\p2pCreditLoanByMonth1395049644360.docx";
        String destFile = "D:\\contractPDF\\20140317\\p2pCreditLoanByMonth1395049644360.pdf";
        office2PDF(sourceFile, destFile);
    }

}

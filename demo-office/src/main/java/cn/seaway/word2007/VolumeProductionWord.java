/*
 * Project: POI
 * 
 * File Created at 2014年3月7日 下午5:55:22
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class VolumeProductionWord {
    public void wordReplace(String file, String newFile, Map<String, String> map) throws IOException, DocumentException {
        try {
            FileInputStream in = new FileInputStream(new File(file));
            HWPFDocument hdt = new HWPFDocument(in);
            // 读取word文本内容
            Range range = hdt.getRange();
            // 替换文本内容
            for (Entry<String, String> entry : map.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            FileOutputStream out = new FileOutputStream(newFile.replace(".docx", ".doc"), true);
            hdt.write(ostream);
            // 输出字节流
            out.write(ostream.toByteArray());
            out.close();
            ostream.close();
            System.out.println("2003-word-只可以导出2003版本...");
            System.out.println("2003-word-Success...");
        } catch (Exception ex) {
            String WordName = newFile.split("/")[newFile.split("/").length - 1];// word
                                                                                // is
                                                                                // name
            String SaveURL = newFile.replace(WordName, "");
            /**解压word**/
            File file1 = new File(file);// 取得word文件
            String dir = SaveURL + "\\zipFile\\";// 取得要解压缩文件到的目录
            FileInputStream inputStream = new FileInputStream(file1);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            ZipEntry entry = null;
            byte ch[] = new byte[256];
            while ((entry = zipInputStream.getNextEntry()) != null) {
                File zFile = new File(dir + entry.getName());
                if (entry.isDirectory()) {
                    if (!zFile.exists()) {
                        zFile.mkdirs();
                    }
                    zipInputStream.closeEntry();
                } else {
                    File fpath = new File(zFile.getParent());
                    if (!fpath.exists()) {
                        fpath.mkdirs();
                    }
                    FileOutputStream outputStream = new FileOutputStream(zFile);
                    int i;
                    while ((i = zipInputStream.read(ch)) != -1) {
                        outputStream.write(ch, 0, i);
                    }
                    zipInputStream.closeEntry();
                    outputStream.close();
                }
            }
            inputStream.close();
            System.out.println("word解压成功...");
            /**替换内容**/
            String documentXMLUrl = SaveURL + "\\zipFile\\word\\document.xml";
            SAXReader reader = new SAXReader();
            Document document = reader.read(documentXMLUrl);
            Element _root = document.getRootElement();
            String xml = _root.asXML();
            for (Entry<String, String> _entry : map.entrySet()) {
                xml = xml.replace(_entry.getKey(), _entry.getValue());
            }
            document = reader.read(new ByteArrayInputStream(xml.getBytes()));
            XMLWriter writer = null;
            writer = new XMLWriter(new FileWriter(documentXMLUrl));
            writer.write(document);
            writer.close();
            System.out.println("word替换成功...");
            /**压缩成word**/
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(newFile))); // 要压缩文件的路径及名称
            String root = SaveURL + "\\zipFile\\";
            String current = SaveURL + "\\zipFile\\";
            File rootFile = new File(root);
            File currentFile = new File(current);
            addAllFiles(zipOutputStream, rootFile, currentFile);
            zipOutputStream.close();
            System.out.println("word压缩成功...");
            /**删除解压word相关文件**/
            if (deleteDirectory(root)) {
                System.out.println("成功删除多余文件...");
            } else {
                System.out.println("删除多余文件失败...");
            }
            System.out.println("2007-word-Success...");
        }
    }

    /**压缩文件**/
    private void addAllFiles(ZipOutputStream zipOutputStream, File current, File root) throws IOException {
        byte buffer[] = new byte[4096];
        int bytesIndex;
        String entries[] = current.list();
        for (int i = 0; i < entries.length; i++) {
            File f = new File(current, entries[i]);
            if (f.isDirectory()) {
                addAllFiles(zipOutputStream, f, root);
                continue;
            }
            String relativePath = getRelativePath(current, root);
            FileInputStream fileInputStream = new FileInputStream(f);
            if (!relativePath.equals("")) {
                relativePath = relativePath + "/";
            }
            ZipEntry entry = new ZipEntry(relativePath + f.getName());
            zipOutputStream.putNextEntry(entry);
            while ((bytesIndex = fileInputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, bytesIndex);
            }
            fileInputStream.close();
        }
    }

    /**获取相对路径**/
    private static String getRelativePath(File currentFile, File rootFile) {
        int len = rootFile.getPath().length();
        String rePath = currentFile.getPath().substring(len);
        if (rePath.length() > 0) {
            rePath = rePath.substring(1);
        }
        return rePath;
    }
    
    /**
    * 删除目录（文件夹）以及目录下的文件
    * @param   sPath 被删除目录的文件路径
    * @return  目录删除成功返回true，否则返回false
    */
    public boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * 删除单个文件
    * @param   sPath    被删除文件的文件名
    * @return 单个文件删除成功返回true，否则返回false
    */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 
     * 被替换的表格列--标的信息
     * @return
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月8日 下午1:25:01  	|  刘章盛     | 创建
     */
    public static String beReplacedXml() {
        StringBuffer tr = new StringBuffer();
        tr.append("<w:tr>");
        tr.append("<w:tblPrEx>");
        tr.append("<w:tblBorders>");
        tr.append("<w:top w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("<w:left w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("<w:bottom w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("<w:right w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("<w:insideH w:val=\"none\" w:color=\"auto\" w:sz=\"0\" w:space=\"0\"/>");
        tr.append("<w:insideV w:val=\"none\" w:color=\"auto\" w:sz=\"0\" w:space=\"0\"/>");
        tr.append("</w:tblBorders>");
        tr.append("<w:tblLayout w:type=\"fixed\"/>");
        tr.append("<w:tblCellMar>");
        tr.append("<w:top w:w=\"15\" w:type=\"dxa\"/>");
        tr.append("<w:left w:w=\"15\" w:type=\"dxa\"/>");
        tr.append("<w:bottom w:w=\"15\" w:type=\"dxa\"/>");
        tr.append("<w:right w:w=\"15\" w:type=\"dxa\"/>");
        tr.append("</w:tblCellMar>");
        tr.append("</w:tblPrEx>");
        tr.append("<w:trPr>");
        tr.append("<w:trHeight w:val=\"239\" w:hRule=\"atLeast\"/>");
        tr.append("</w:trPr>");
        tr.append("<w:tc>");
        tr.append("<w:tcPr>");
        tr.append("<w:tcW w:w=\"8398\" w:type=\"dxa\"/>");
        tr.append("<w:gridSpan w:val=\"4\"/>");
        tr.append("<w:tcBorders>");
        tr.append("<w:top w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("<w:left w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("<w:bottom w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("<w:right w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        tr.append("</w:tcBorders>");
        tr.append("<w:tcMar>");
        tr.append("<w:top w:w=\"15\" w:type=\"dxa\"/>");
        tr.append("<w:left w:w=\"150\" w:type=\"dxa\"/>");
        tr.append("<w:bottom w:w=\"15\" w:type=\"dxa\"/>");
        tr.append("<w:right w:w=\"15\" w:type=\"dxa\"/>");
        tr.append("</w:tcMar>");
        tr.append("<w:vAlign w:val=\"center\"/>");
        tr.append("</w:tcPr>");
        tr.append("<w:p>");
        tr.append("<w:pPr>");
        tr.append("<w:widowControl/>");
        tr.append("<w:jc w:val=\"center\"/>");
        tr.append("<w:rPr>");
        tr.append("<w:rFonts w:hint=\"eastAsia\"/>");
        tr.append("<w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/>");
        tr.append("</w:rPr>");
        tr.append("</w:pPr>");
        tr.append("<w:r>");
        tr.append("<w:rPr>");
        tr.append("<w:rFonts w:hint=\"eastAsia\"/>");
        tr.append("<w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/>");
        tr.append("</w:rPr>");
        tr.append("<w:t>$borrowerRescPlan$</w:t>");
        tr.append("</w:r>");
        tr.append("</w:p>");
        tr.append("</w:tc>");
        tr.append("</w:tr>");

        return tr.toString();
    }

    /**
     * 
     * 被替换的表格列--债权信息
     * @return
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月8日 下午1:25:01  	|  刘章盛     | 创建
     */
    public static String beReplacedXmlTwo() {
        String tr = beReplacedXml()
                .replace("<w:trHeight w:val=\"239\" w:hRule=\"atLeast\"/>",
                        "<w:trHeight w:val=\"202\" w:hRule=\"atLeast\"/>")
                .replace("<w:tcW w:w=\"8398\" w:type=\"dxa\"/>", "<w:tcW w:w=\"8428\" w:type=\"dxa\"/>")
                .replace("<w:t>$borrowerRescPlan$</w:t>", "<w:t>$assigneeRescPlan$</w:t>");

        return tr;
    }

    /**
     * 
     * 生成要替换的表格列
     * @param map key:2014-02-02 value:¥2001.00
     * @return
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月8日 下午1:25:31  	|  刘章盛     | 创建
     */
    public static String generateXmlTable(LinkedHashMap<String, String> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<w:tr>");
        sb.append("<w:tblPrEx>");
        sb.append("<w:tblBorders>");
        sb.append("<w:top w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:left w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:bottom w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:right w:val=\"single\" w:color=\"000000\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:insideH w:val=\"none\" w:color=\"auto\" w:sz=\"0\" w:space=\"0\"/>");
        sb.append("<w:insideV w:val=\"none\" w:color=\"auto\" w:sz=\"0\" w:space=\"0\"/>");
        sb.append("</w:tblBorders>");
        sb.append("<w:tblLayout w:type=\"fixed\"/>");
        sb.append("<w:tblCellMar>");
        sb.append("<w:top w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:left w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:bottom w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:right w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("</w:tblCellMar>");
        sb.append("</w:tblPrEx>");
        sb.append("<w:trPr>");
        sb.append("<w:trHeight w:val=\"239\" w:hRule=\"atLeast\"/>");
        sb.append("</w:trPr>");
        sb.append("<w:tc>");
        sb.append("<w:tcPr>");
        sb.append("<w:tcW w:w=\"2799\" w:type=\"dxa\"/>");
        sb.append("<w:gridSpan w:val=\"2\"/>");
        sb.append("<w:tcBorders>");
        sb.append("<w:top w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:left w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:bottom w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:right w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("</w:tcBorders>");
        sb.append("<w:tcMar>");
        sb.append("<w:top w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:left w:w=\"150\" w:type=\"dxa\"/>");
        sb.append("<w:bottom w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:right w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("</w:tcMar>");
        sb.append("<w:vAlign w:val=\"center\"/>");
        sb.append("</w:tcPr>");
        sb.append("<w:p>");
        sb.append("<w:pPr>");
        sb.append("<w:widowControl/>");
        sb.append("<w:jc w:val=\"center\"/>");
        sb.append("<w:rPr>");
        sb.append("<w:rFonts w:hint=\"eastAsia\" w:eastAsia=\"宋体\"/>");
        sb.append("<w:lang w:eastAsia=\"zh-CN\"/>");
        sb.append("</w:rPr>");
        sb.append("</w:pPr>");
        sb.append("<w:r>");
        sb.append("<w:rPr>");
        sb.append("<w:rFonts w:hint=\"eastAsia\"/>");
        sb.append("<w:lang w:eastAsia=\"zh-CN\"/>");
        sb.append("</w:rPr>");
        sb.append("<w:t>第1期</w:t>");
        sb.append("</w:r>");
        sb.append("</w:p>");
        sb.append("</w:tc>");
        sb.append("<w:tc>");
        sb.append("<w:tcPr>");
        sb.append("<w:tcW w:w=\"2799\" w:type=\"dxa\"/>");
        sb.append("<w:tcBorders>");
        sb.append("<w:top w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:left w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:bottom w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:right w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("</w:tcBorders>");
        sb.append("<w:tcMar>");
        sb.append("<w:top w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:left w:w=\"150\" w:type=\"dxa\"/>");
        sb.append("<w:bottom w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:right w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("</w:tcMar>");
        sb.append("<w:vAlign w:val=\"center\"/>");
        sb.append("</w:tcPr>");
        sb.append("<w:p>");
        sb.append("<w:pPr>");
        sb.append("<w:widowControl/>");
        sb.append("<w:jc w:val=\"center\"/>");
        sb.append("<w:rPr>");
        sb.append("<w:rFonts w:hint=\"eastAsia\"/>");
        sb.append("<w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/>");
        sb.append("</w:rPr>");
        sb.append("</w:pPr>");
        sb.append("<w:r>");
        sb.append("<w:rPr>");
        sb.append("<w:rFonts w:hint=\"eastAsia\"/>");
        sb.append("<w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/>");
        sb.append("</w:rPr>");
        sb.append("<w:t>2014-01-01</w:t>");
        sb.append("</w:r>");
        sb.append("</w:p>");
        sb.append("</w:tc>");
        sb.append("<w:tc>");
        sb.append("<w:tcPr>");
        sb.append("<w:tcW w:w=\"2800\" w:type=\"dxa\"/>");
        sb.append("<w:tcBorders>");
        sb.append("<w:top w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:left w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:bottom w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("<w:right w:val=\"outset\" w:color=\"auto\" w:sz=\"6\" w:space=\"0\"/>");
        sb.append("</w:tcBorders>");
        sb.append("<w:tcMar>");
        sb.append("<w:top w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:left w:w=\"150\" w:type=\"dxa\"/>");
        sb.append("<w:bottom w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("<w:right w:w=\"15\" w:type=\"dxa\"/>");
        sb.append("</w:tcMar>");
        sb.append("<w:vAlign w:val=\"center\"/>");
        sb.append("</w:tcPr>");
        sb.append("<w:p>");
        sb.append("<w:pPr>");
        sb.append("<w:widowControl/>");
        sb.append("<w:jc w:val=\"center\"/>");
        sb.append("<w:rPr>");
        sb.append("<w:rFonts w:hint=\"eastAsia\"/>");
        sb.append("<w:lang w:eastAsia=\"zh-CN\"/>");
        sb.append("</w:rPr>");
        sb.append("</w:pPr>");
        sb.append("<w:r>");
        sb.append("<w:t>¥2001.00</w:t>");
        sb.append("</w:r>");
        sb.append("</w:p>");
        sb.append("</w:tc>");
        sb.append("</w:tr>");

        String num = "第1期";
        String date = "2014-01-01";
        String money = "¥2001.00";

        int idxOne = sb.indexOf(num);
        int idxTwo = sb.indexOf(date);
        int idxThree = sb.indexOf(money);

        StringBuffer result = new StringBuffer();
        int i = 1;
        for (Entry<String, String> entry : map.entrySet()) {
            sb.replace(idxOne, idxOne + num.length(), "第" + i + "期");
            sb.replace(idxTwo, idxTwo + date.length(), entry.getKey());
            sb.replace(idxThree, idxThree + money.length(), entry.getValue());

            result.append(sb);
            i++;
        }

        return result.toString();
    }

    /**
    * @param args
    * @throws DocumentException
    */
    public static void main(String[] args) throws DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("$protocolNumber$", "201403062022");
        map.put("$effectDate$", "2014年3月6日");
        map.put("$investorUseName$", "章张账");
        map.put("$investorIdCardNo$", "333782200101010038");
        map.put("$investorLoginName$", "一只小狗");
        map.put("$assigneeUseName$", "五无误");
        map.put("$assigneeIdCardNo$", "444782201001011234");
        map.put("$assigneeUseLoginName$", "雪纳瑞");
        map.put("$iteTitle$", "借款买车");
        map.put("$borrowerUseName$", "王老板");
        map.put("$iteLimitYuan$", "30000.00");
        map.put("$iteYearRate$", "18");
        map.put("$iteRepayDate$", "5个月");
        map.put("$iteFullSecondApprovalTime$", "2014-01-01");
        map.put("$iteRepayDeadline$", "2014-07-07");
        map.put("$iteRepaySumYuan$", "31000.00");
        map.put("$iteRepayType$", "按月还本付息");
        map.put("$claTransClaimSumYuan$", "2000.00");
        map.put("$claTransSumYuan$", "1900.00");
        map.put("$claTransChargeYuan$", "10.00");
        map.put("$assignSecondApprovalTime$", "2014-02-02");

        LinkedHashMap<String, String> itemMap = new LinkedHashMap<String, String>();
        itemMap.put("2014-02-02", "¥2100.00");
        itemMap.put("2014-03-02", "¥2200.00");
        itemMap.put("2014-04-02", "¥2300.00");
        
        LinkedHashMap<String, String> claimMap = new LinkedHashMap<String, String>();
        claimMap.put("2014-02-02", "¥2100.00");
        claimMap.put("2014-03-02", "¥2200.00");
        claimMap.put("2014-04-02", "¥2300.00");

        map.put(beReplacedXml(), generateXmlTable(itemMap));
        map.put(beReplacedXmlTwo(), generateXmlTable(claimMap));
        VolumeProductionWord word = new VolumeProductionWord();
        try {
            String timeMillis = String.valueOf(System.currentTimeMillis());
            String srcPath = "D:\\doc\\易通贷借款协议债券转让每月等额还款.docx";
            String destPath = "D:\\doc\\易通贷借款协议债券转让每月等额还款"+timeMillis+".docx";
            word.wordReplace(srcPath, destPath, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

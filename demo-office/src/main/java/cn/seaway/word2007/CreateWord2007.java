/*
 * Project: POI
 * 
 * File Created at 2013年12月6日 下午5:51:28
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

import cn.seaway.util.FileUtil;

/**
 * poi word
 * <br>----------------------------------------------------变更记录--------------------------------------------------
 * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
 * <br> 0     | 2013年12月6日 下午5:51:28  	|  刘章盛     | 创建  
 */
public class CreateWord2007 {

    private static final String suffix = ".docx";

    public static void main(String[] args) throws Exception {
        String diskPath = "D:\\doc\\";
        String folderPath = "generate\\";
        String fileName = "【1-3】【文档】易通贷抵押贷借款协议（每月等额还款）140218";
        String srcPath = diskPath + fileName + suffix;
        String timeMillis = String.valueOf(System.currentTimeMillis());
        String targetFileName = fileName.replace("140218", timeMillis);
        FileUtil.createDir(diskPath + folderPath);
        String destPath = diskPath + folderPath + targetFileName + suffix;
        Map<String, String> map = new HashMap<String, String>();
        map.put("$protocolNumber$", "201403062022");
        map.put("$effectDate$", "2014年3月6日");
        map.put("$iteTitle$", "借款买车");
        map.put("$investorIdCardNo$", "333***0038");
        map.put("$investorLoginName$", "nimeia");
        map.put("$investorUseName$", "章**");
        map.put("$borrowerIdCardNo$", "555***1234");
        map.put("$borrowerLoginName$", "wowowo");
        map.put("$borrowerUseName$", "张**");
        map.put("$interestManageFeeRatio$", "10");
        map.put("$claInitSumYuan$", "3000.00");
        map.put("$iteYearRate$", "18");
        map.put("$claCreateTime$", "2014-05-12");
        map.put("$iteRepayDeadline$", "2014-07-07");
        map.put("$iteFullSecondApprovalTime$", "2014-01-01");
        map.put("$iteRepayDate$", "5个月");
        map.put("$iteReason$", "买个女兵");
        map.put("$iteRepayType$", "按月还本付息");
        map.put("$colNameSet$", "1.宝马车;2.别墅");
        map.put("$colDescSet$", "1.好车.2.好房子");
        map.put("$overdueRatio$", "0.2");

        searchAndReplace(srcPath, destPath, map);
    }

    public static void searchAndReplace(String srcPath, String destPath, Map<String, String> map) {
        XWPFDocument doc;
        try {
            doc = new XWPFDocument(OPCPackage.open(srcPath));
            Set<String> set = map.keySet();
            // 替换段落中的文字
            Iterator<XWPFParagraph> itPara = doc.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    List<XWPFRun> run = paragraph.getRuns();
                    for (int i = 0; i < run.size(); i++) {
                        XWPFRun ru = run.get(i);
                        if (null != ru.getText(ru.getTextPosition()) && ru.getText(ru.getTextPosition()).equals(key)) {
                            // 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始就可以把原来的文字全部替换掉了
                            ru.setText(map.get(key), 0);
                        }
                    }
                }
            }
            // 替换表格中的文字
            Iterator<XWPFTable> iteTable = doc.getTablesIterator();
            while (iteTable.hasNext()) {
                XWPFTable table = (XWPFTable) iteTable.next();
                int rcount = table.getNumberOfRows();
                for (int i = 0; i < rcount; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        String text = cell.getText();
                        String textV = text;// 一个单元格里要替换多个值时要这样做
                        for (Entry<String, String> e : map.entrySet()) {
                            if (text.indexOf(e.getKey()) != -1) {
                                textV = textV.replace(e.getKey(), e.getValue());
                            }
                        }
                        if (!text.equals(textV)) {
                            cell.removeParagraph(0);// 把单元格原来的内容移除
                            cell.setText(textV);
                        }
                    }
                }
            }

            FileOutputStream outStream = new FileOutputStream(destPath);
            doc.write(outStream);
            outStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * 解析word2007
     * @param filePath
     * @param fileName
     * @return
     * @throws Exception
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月6日 上午9:57:08  	|  刘章盛     | 创建
     */
    public static String readDoc2007(String filePath, String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(new File(filePath + fileName));
        XWPFDocument doc = new XWPFDocument(fis);
        // 所有的图片
        List<XWPFPictureData> picList = doc.getAllPictures();
        for (XWPFPictureData xwpfPictureData : picList) {
            System.out.println("图片名称:" + xwpfPictureData.getFileName());
        }

        List<XWPFParagraph> parList = doc.getParagraphs();
        StringBuffer sb = new StringBuffer();
        for (XWPFParagraph p : parList) {
            List<XWPFRun> runList = p.getRuns();
            StringBuffer para = new StringBuffer();
            for (XWPFRun r : runList) {
                CTR ctr = r.getCTR();
                List<CTText> cttList = ctr.getTList();
                for (CTText ctText : cttList) {
                    System.out.println(ctText.getStringValue());
                }
                String text = r.getText(r.getTextPosition());
                StringBuffer style = new StringBuffer();
                if (null != text) {
                    if (r.isBold()) {
                        style.append("font-weight:bold;");
                    }
                    if (r.isItalic()) {
                        style.append("font-style:italic;");
                    }
                    if (r.isStrike()) {
                        style.append("text-decoration:line-through;");
                    }
                    Integer fontSize = r.getFontSize();
                    style.append("font-size:" + fontSize + "pt; ");
                    String fontFamily = r.getFontFamily();
                    if (null != fontFamily) {
                        style.append("font-family:" + fontFamily + "; ");
                    }
                    int value = r.getSubscript().getValue();
                    if (value == 2) {
                        style.append("vertical-align:super ");
                    } else if (value == 3) {
                        style.append("vertical-align:sub ");
                    }
                    String charact = "<span  style= '" + style.toString() + "'>" + text + "</span>";
                    para.append(charact);
                }
                List<XWPFPicture> epicList = r.getEmbeddedPictures();
                for (XWPFPicture epic : epicList) {
                    XWPFPictureData epicData = epic.getPictureData();
                    String picName = epicData.getFileName();
                    String imgUrl = filePath + picName;
                    byte[] d = epicData.getData();
                    RandomAccessFile rf = new RandomAccessFile(imgUrl, "rw");
                    rf.write(d);
                    rf.close();

                    File file = new File(imgUrl);
                    BufferedImage image = ImageIO.read(file);
                    int height = image.getHeight();
                    int width = image.getWidth();

                    para.append("<img src=temp/" + picName + " style=width:" + width + "px;height:" + height + "px />");

                }
            }
            para.append("<br/>");
            sb.append(para);
        }
        return sb.toString();
    }

    public static Map<String, Object> getWord2007Count(String filePath) {
        Map<String, Object> content = new HashMap<String, Object>();

        XWPFDocument docx2;
        try {
            docx2 = new XWPFDocument(OPCPackage.open(filePath));
            int pages = docx2.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();// 总页数
            int characters = docx2.getProperties().getExtendedProperties().getUnderlyingProperties().getCharacters();// 不带空格的总字数
            content.put("pages", pages);
            content.put("characters", characters);
            System.out.println(pages);
            System.out.println(characters);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static String getWord2007Content(String filePath) {
        XWPFWordExtractor docx;
        try {
            docx = new XWPFWordExtractor(OPCPackage.open(filePath));
            String text = docx.getText();
            System.out.println(text);
            return text;
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

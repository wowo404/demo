/*
 * Project: POI
 * 
 * File Created at 2014年3月6日 下午2:02:23
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 解析word2003
 * @author JustingLiu
 *
 */
public class ReadAndWrite2003 {
    
    public static void main(String[] args) {
        String sourceFile = "D:/doc/02B--sifang.doc";
        String wordFile = "D:/doc/02A--3.doc";
        int a = word2003Replace(sourceFile, wordFile, getMap());
        System.out.println(a);
    }
    
    /**
     * 替换word2003模板中的内容
     * @param srcFilePath
     * @param newFile
     * @param map
     * @return
     * @author JustingLiu
     */
    public static int word2003Replace(String srcFilePath,String newFile,Map<String,String> map){
        try {
            FileInputStream in = new FileInputStream(new File(srcFilePath));
            HWPFDocument hdt = new HWPFDocument(in);
            // 读取word文本内容
            Range range = hdt.getRange();
            // 替换文本内容
            for (Map.Entry<String, String> entry : map.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            FileOutputStream out = new FileOutputStream(newFile.replace(".docx", ".doc"), true);
            hdt.write(ostream);
            // 输出字节流
            out.write(ostream.toByteArray());
            out.close();
            ostream.close();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static void exportWord2003(String file, String content) {
        try {
            InputStream is = new ByteArrayInputStream(content.getBytes());
            POIFSFileSystem fs = new POIFSFileSystem();
            DirectoryEntry entry = fs.getRoot();
            entry.createDocument("WordDocument", is);
            FileOutputStream fos = new FileOutputStream(file);
            fs.writeFilesystem(fos);
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 解析word2003
     * @param path  文件路径  
     * @param fn  文件名
     * @return  
     * @throws IOException
     */
    public static String readDoc2003(String path, String fn) throws IOException {
        FileInputStream in = new FileInputStream(new File(path + fn));
        HWPFDocument document = new HWPFDocument(in);
        Range r = document.getRange();
        int pNums = r.numParagraphs();

        int picSize = 0;
        StringBuffer sb = new StringBuffer();
        System.out.println("pNums:" + pNums);
        for (int i = 0; i < pNums; i++) {

            Paragraph p = r.getParagraph(i);
            int lenChar = p.numCharacterRuns(); // 当前段落中的字对象数
            PicturesTable pTable = document.getPicturesTable();
            StringBuffer numSb = new StringBuffer();
            for (int y = 0; y < lenChar; y++) { // 对当前段落中的字对象循环
                CharacterRun run = p.getCharacterRun(y);
                StringBuffer ptext = new StringBuffer();
                if (!pTable.hasPicture(run)) {
                    String charact = run.text();
                    while (charact.indexOf(" ") > -1) {
                        charact = charact.replace(" ", "&nbsp;");// 空格转换
                    }
                    if (charact.length() > 0) {
                        if (charact.charAt(0) == 11) {
                            charact = "<br>";
                        }
                    }

                    if (y == lenChar - 1) {
                        if (charact.charAt(0) == 13) {
                            charact = "&nbsp;";
                        } else {
                            charact = charact.substring(0, charact.length() - 1);
                        }
                    }

                    String style = "";
                    if (run.isBold())
                        style += "font-weight:bold;";
                    if (run.isItalic())
                        style += "font-style:italic;";
                    if (run.isStrikeThrough())
                        style += "text-decoration:line-through;;";
                    int fontSize = run.getFontSize();
                    style += "font-size:" + fontSize / 2 + "pt;";
                    picSize = fontSize;
                    String fontName = run.getFontName();
                    style += "font-family:" + fontName + ";";
                    if (y != lenChar - 1) {
                        short sssi = run.getSubSuperScriptIndex();
                        if (sssi != 0 && sssi == 2)
                            style += "vertical-align: sub;";
                        if (sssi != 0 && sssi == 1)
                            style += "vertical-align: super;";
                    }
                    int fontcolor = run.getIco24();
                    int[] rgb = new int[3];
                    if (fontcolor != -1) {
                        rgb[0] = (fontcolor >> 0) & 0xff; // red;
                        rgb[1] = (fontcolor >> 8) & 0xff; // green
                        rgb[2] = (fontcolor >> 16) & 0xff; // blue
                    }
                    style += "color: rgb(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")";
                    charact = "<span  style='" + style + "'>" + charact + "</span>";
                    ptext.append(charact);
                } else {

                    Picture pic = pTable.extractPicture(run, false);
                    int width = pic.getWidth();
                    int height = pic.getHeight();

//                    int ratiox = pic.getWidth() * pic.getAspectRatioX() / 100;
//                    int ratioy = pic.getHeight() * pic.getAspectRatioY() / 100;
                    String fileName = pic.suggestFullFileName();
                    String imgUrl = path + fileName;
                    System.out.println("imgUrl:" + imgUrl);

                    if (fileName.contains(".wmf")) {
                        byte[] b = pic.getContent();
                        RandomAccessFile rf = new RandomAccessFile(imgUrl, "rw");
                        rf.write(b);
                        rf.close();

                        int myHeight = 0;
                        try {
                            String imgPath = wmfToJpg(imgUrl);
                            System.out.println("imgurl:" + imgUrl);
                            System.out.println(StringUtils.replace(fileName, "wmf", "png"));
                            File file = new File(imgPath);
                            BufferedImage image = null;
                            image = ImageIO.read(file);
                            int pheight = image.getHeight();
                            int pwidth = image.getWidth();

                            myHeight = (int) (pheight / (pwidth / picSize * 1.0) * 1.5);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ptext.append("<img src=temp/" + StringUtils.replace(fileName, "wmf", "png")
                                + " style='vertical-align:middle;width:" + picSize * 1.5 + "px;height:" + myHeight
                                + "px' />");

                    } else {
                        OutputStream out = new FileOutputStream(new File(imgUrl));
                        pic.writeImageContent(out);
                        //TODO:这里width原来是ratiox，height原来是ratioy
                        ptext.append("<img src=temp/" + fileName + " style=width:" + width + "px;height:" + height
                                + "px />");
                    }
                }

                numSb.append(ptext);

            }

            sb.append("<br/>").append(numSb);
        }
        String s = sb.toString();
        String ss = s.replaceAll("EMBED&nbsp;Equation.3", "");
        return ss;
    }

    /**
     * 将wmf图片转成jpg图片
     * @param wmfPath
     * @throws Exception 
     */
    public static String wmfToJpg(String wmfPath) throws Exception {
        File wmf = new File(wmfPath);
        FileInputStream wmfStream = new FileInputStream(wmf);
        ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
        int noOfByteRead = 0;
        while ((noOfByteRead = wmfStream.read()) != -1) {
            imageOut.write(noOfByteRead);
        }
        imageOut.flush();
        wmfStream.close();

        WMFTranscoder transcoder = new WMFTranscoder();
        TranscodingHints hints = new TranscodingHints();
        hints.put(ImageTranscoder.KEY_WIDTH, 20f);
        hints.put(ImageTranscoder.KEY_HEIGHT, 20f);
        transcoder.setTranscodingHints(hints);
        TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(imageOut.toByteArray()));
        ByteArrayOutputStream svg = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(svg);
        transcoder.transcode(input, output);
        String svgFile = StringUtils.replace(wmfPath, "wmf", "svg");
        FileOutputStream fileOut = new FileOutputStream(svgFile);
        fileOut.write(svg.toByteArray());
        fileOut.flush();
        fileOut.close();

        ImageTranscoder it = new JPEGTranscoder();

        ByteArrayOutputStream jpg = new ByteArrayOutputStream();
        it.transcode(new TranscoderInput(new ByteArrayInputStream(svg.toByteArray())), new TranscoderOutput(jpg));
        String jpgFile = StringUtils.replace(wmfPath, "wmf", "png");
        FileOutputStream jpgOut = new FileOutputStream(jpgFile);
        jpgOut.write(jpg.toByteArray());
        jpgOut.flush();
        jpgOut.close();
        return jpgFile;
    }
    
    private static Map<String, String> getMap() {
        String type = "4";
        Map<String, String> map = new HashMap<String, String>();
        map.put("$protocolNumber$", "20140202010101000001");
        map.put("$effectDate$", "2014年02月02日");
        map.put("$effectYear$", "2014");
        map.put("$effectMonth$", "02");
        map.put("$effectDay$", "21");
        map.put("$iteTitle$", "借钱啊");
        map.put("$investorIdCardNo$", "360782198709020035");
        map.put("$investorLoginName$", "liutest");
        map.put("$investorUseName$", "刘是是是是");
        map.put("$investorAddressNow$", "中过广东奖励");
        map.put("$investorMobile$", "15058124996");
        map.put("$investorEmail$", "sss@dd.com");
        map.put("$borrowerUseName$", "即可麻将");
        map.put("$borrowerIdCardNo$", "364456198512120014");
        map.put("$iteRepayDateWithName$", "3个月");
        map.put("$iteReason$", "借钱度日");
        map.put("$iteLimitYuan$", "5000000.00");
        // 5-转让
        if (!type.equals("5")) {
            map.put("$iteYearRateFd$", "12.01");// 借款协议-转让协议
            map.put("$iteRepayDeadline$", "2014-12-14");// 借款协议
            map.put("$iteFullSecondApprovalTime$", "2014-12-03");// 借款协议
            map.put("$interestManageFeeRatio$", "1.04");// 借款协议
            map.put("$borrowerLoginName$", "jietest");// 借款协议
            map.put("$borrowerAddressNow$", "美国加利福尼亚");// 借款协议
            map.put("$borrowerEmail$", "fdf@qq.com");// 借款人email
            map.put("$claInitSumYuan$", "6541.01");// 借款协议
            map.put("$claCreateTime$", "2014-12-06");// 借款协议
            map.put("$overdueRatio$", "10.5");// 借款协议
            map.put("$iteRepaySumYuan$", "6542.05");// 借款协议
            map.put("$loanCNWrite$", "陆千捌百两角五分");
            map.put("$t$", "2");
            map.put("$w$", "5");
            map.put("$q$", "5");
            map.put("$b$", "5");
            map.put("$s$", "5");
            map.put("$y$", "5");
            map.put("$j$", "5");
            map.put("$f$", "5");
            map.put("$repayTypeWord$", "5");
            map.put("$repayTypeWord02B$", "5");
            map.put("$repayCNWrite$", "5");
            map.put("$rt$", "5");
            map.put("$rw$", "5");
            map.put("$rq$", "5");
            map.put("$rb$", "5");
            map.put("$rs$", "5");
            map.put("$ry$", "5");
            map.put("$rj$", "5");
            map.put("$rf$", "5");
            map.put("$repayDay$", "5");
            map.put("$firstRepayDate$", "2014年12月12日");
            map.put("$lastRepayDate$", "2012年05月04日");
            map.put("$sponsorName$", "");
            map.put("$sponsorWebsite$", "");
            map.put("$sponsorContactAddress$", "");
            map.put("$sponsorSignatureTime$", "");
            map.put("$investorDueMoney$", "5421.21");
        } else {
            map.put("$claId$", "521");// 转让
            map.put("$claTransFirstRepayDate$", "1987年3月3日");
            map.put("$assigneeUseName$", "我辅导费");// 转让
            map.put("$assigneeIdCardNo$", "364123187514120210");// 转让
            map.put("$assigneeUseLoginName$", "ooo");// 转让
            map.put("$assigneeAddressNow$", "不知道");
            map.put("$assigneeEmail$", "dfd@df.com");
            map.put("$claTransClaimSumYuan$", "5412.65");// 转让
            map.put("$claTransSumYuan$", "4521.1");// 转让
            map.put("$claTransChargeYuan$", "5421.45");// 转让
            map.put("$surplusDateWithName$", "90天");
        }
        return map;
    }
}

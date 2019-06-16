/*
 * Project: POI
 * 
 * File Created at 2014年3月7日 上午10:35:13
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

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class CreateTableWithPOI {
    public static void main(String[] args) {
        String outputFile = "D:\\test.docx";
        CustomXWPFDocument document = new CustomXWPFDocument();
        XWPFTable tableOne = document.createTable();
        XWPFTableRow tableOneRowOne = tableOne.getRow(0);
        tableOneRowOne.getCell(0).setText("第1行第1列");
        tableOneRowOne.addNewTableCell().setText("第1行第2列");
        tableOneRowOne.addNewTableCell().setText("第1行第3列");
        tableOneRowOne.addNewTableCell().setText("第1行第4列");
        XWPFTableRow tableOneRowTwo = tableOne.createRow();
        tableOneRowTwo.getCell(0).setText("第2行第1列");
        tableOneRowTwo.getCell(1).setText("第2行第2列");
        tableOneRowTwo.getCell(2).setText("第2行第3列");
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(outputFile);
            ByteArrayInputStream in = getPieChartImage();
            String ind = document.addPictureData(in, XWPFDocument.PICTURE_TYPE_JPEG);
            System.out.println("pic ID=" + ind);
            XWPFParagraph para = document.createParagraph();
            document.createPicture(para, document.getAllPictures().size() - 1, 200, 200, "    ");
            // 放第二张图
            ind = document.addPictureData(getBarChartImage(), XWPFDocument.PICTURE_TYPE_JPEG);
            System.out.println("pic ID=" + ind);
            XWPFParagraph para2 = document.createParagraph();
            document.createPicture(para2, document.getAllPictures().size() - 1, 200, 200, "    ");
            document.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ByteArrayInputStream getPieChartImage() {
        ByteArrayInputStream in = null;
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue(" 北京局 ", 20);
        pieDataset.setValue(" 上海局 ", 18);
        pieDataset.setValue(" 天津局 ", 16);
        pieDataset.setValue(" 重庆局 ", 15);
        pieDataset.setValue(" 山东局 ", 45);
        JFreeChart chart = ChartFactory.createPieChart3D(" 企业备案图 ", pieDataset, true, false, false);
        // 设置标题字体样式
        chart.getTitle().setFont(new Font(" 黑体 ", Font.BOLD, 20));
        // 设置饼状图里描述字体样式
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setLabelFont(new Font(" 黑体 ", Font.BOLD, 10));
        // 设置显示百分比样式
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator((" {0}({2}) "),
                NumberFormat.getNumberInstance(), new DecimalFormat(" 0.00% ")));
        // 设置统计图背景
        piePlot.setBackgroundPaint(Color.white);
        // 设置图片最底部字体样式
        chart.getLegend().setItemFont(new Font(" 黑体 ", Font.BOLD, 10));
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(out, chart, 400, 300);
            in = new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }

    public static ByteArrayInputStream getBarChartImage() {
        ByteArrayInputStream in = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "Spring　Security", "Jan");
        dataset.addValue(200, "jBPM　4", "Jan");
        dataset.addValue(300, "Ext　JS", "Jan");
        dataset.addValue(400, "JFreeChart", "Jan");
        JFreeChart chart = ChartFactory.createBarChart("chart", "num", "type", dataset, PlotOrientation.VERTICAL, true,
                false, false);
        // 设置标题字体样式
        chart.getTitle().setFont(new Font(" 黑体 ", Font.BOLD, 20));
        // 设置饼状图里描述字体样式
        // 设置图片最底部字体样式
        chart.getLegend().setItemFont(new Font(" 黑体 ", Font.BOLD, 10));
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(out, chart, 400, 300);
            in = new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }

    public static void createParagraphContent(CustomXWPFDocument doc, String content) {
        XWPFRun title = doc.createParagraph().createRun();// 设置一个新的段落
        title.setText(content);
        title.setFontFamily("宋体");
        title.setBold(true);
    }
}

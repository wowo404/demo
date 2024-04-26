package org.demo.itextpdf7.advanced;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author lzs
 * @Date 2024/3/20 11:37
 **/
public class TicketForm {

    static PdfFont songFont;

    static {
        try {
            songFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Color customColor = new DeviceRgb(244, 248, 249);

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        String dest = "E:\\downloads\\ticket-form.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        doc.add(new Paragraph("XXX集团公开招聘").setFont(songFont)
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold());
        doc.add(new Paragraph("笔试准考证").setFont(songFont)
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold());
        doc.add(createTable());
        doc.add(createList());

        doc.close();
    }

    private static Table createTable() {
        Table table = new Table(UnitValue.createPointArray(new float[]{14, 20, 14, 20, 20})).useAllAvailableWidth();
        table.setFont(songFont).setBorder(Border.NO_BORDER).setFixedLayout().setMarginTop(8f).setTextAlignment(TextAlignment.RIGHT);
        Cell cell = createKeyCell("姓名");
        table.addCell(cell);
        cell = createValueCell("林俊杰");
        table.addCell(cell);
        cell = createKeyCell("性别");
        table.addCell(cell);
        cell = createValueCell("女");
        table.addCell(cell);
        //照片
        Image image;
        try {
            image = new Image(ImageDataFactory.create("E:\\data\\photos\\edb08a90abab84f15c10e7a63f2b0c88.jpeg"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("生成报名表pdf时，读取照片失败");
        }
        image.setWidth(UnitValue.createPercentValue(100));
        Cell cell23 = new Cell(7, 1).add(image);
        cell23.setBorder(Border.NO_BORDER);
        table.addCell(cell23);

        cell = createKeyCell("身份证号码");
        table.addCell(cell);
        cell = createValueCell("360782199902050021");
        table.addCell(cell);
        cell = createKeyCell("手机号码");
        table.addCell(cell);
        cell = createValueCell("13521023568");
        table.addCell(cell);
        cell = createKeyCell("报考岗位代码");
        table.addCell(cell);
        cell = createValueCell("MPZC001");
        table.addCell(cell);
        cell = createKeyCell("报考岗位名称");
        table.addCell(cell);
        cell = createValueCell("马屁专员");
        table.addCell(cell);
        cell = createKeyCell("准考证号");
        table.addCell(cell);
        cell = createValueCell("BS0001", 1, 3);
        table.addCell(cell);
        cell = createKeyCell("考试地点");
        table.addCell(cell);
        cell = createValueCell("江西省赣州市南康区金融中心市民服务中心2栋101室", 1, 3);
        table.addCell(cell);
        cell = createKeyCell("考场号");
        table.addCell(cell);
        cell = createValueCell("第五考场");
        table.addCell(cell);
        cell = createKeyCell("座位号");
        table.addCell(cell);
        cell = createValueCell("10");
        table.addCell(cell);
        cell = createKeyCell("考试时间");
        table.addCell(cell);
        cell = createValueCell("2021-01-01 12:12 ~ 2022-02-02 12:23", 1, 3);
        table.addCell(cell);
        return table;
    }

    private static Cell createKeyCell(String text) {
        return new Cell().add(new Paragraph(text).setFixedLeading(20f)).setBorder(Border.NO_BORDER);
    }

    private static Cell createValueCell(String text) {
        return createValueCell(text, 1, 1);
    }

    private static Cell createValueCell(String text, int rowspan, int colspan) {
        Paragraph paragraph = new Paragraph(text).setBackgroundColor(customColor).setFixedLeading(20f);
        return new Cell(rowspan, colspan).add(paragraph).setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER).setBold();
    }

    private static List createList() throws MalformedURLException {
        List list = new List()
                .setSymbolIndent(1)
                .setListSymbol("")
                .setFont(songFont);
        ListItem blank = new ListItem("");
        blank.setHeight(15f);
        ListItem notification = new ListItem("考生须知");
        notification.setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(13f);
        Image image = new Image(ImageDataFactory.create("E:\\data\\photos\\edb08a90abab84f15c10e7a63f2b0c88.jpeg"));
        System.out.println("图片高" + image.getImageHeight());
        System.out.println("图片宽" + image.getImageWidth());
        image.scaleToFit(150, 105);
        ListItem imageItem = new ListItem(image);
        imageItem.setFixedPosition(430, 620, UnitValue.createPercentValue(100));
//        list.add("\u59d3\u540d\uff1a\u4e9a\u5386\u514b\u65af\u30fb\u725b\u903c")
        list.add(blank)
                .add(notification)
                .add("1.考生必须持准考证和有效身份证件(身份证，军人、武警人员证件，护照等)入场，证件不全者不得入场。")
                .add("2.考生须在每场考试(含笔试和面试)开考前30分钟入场。开始考试30分钟以后，考生不得入场。")
                .add("3.考生只准携带必要的文具入场，如2B铅笔(涂黑答题卡用)、签字笔、橡皮，禁止携带各种文字材料或通讯工具等。考场内不得擅自相互借用文具。")
                .add("4.考试正式开始后，考生不得中途退场。")
                .add("5.考生在考场内须遵守考场纪律，服从管理，如有考试违规行为，按相关规定进行理。")
//                .add(imageItem)
        ;
        return list;
    }


}

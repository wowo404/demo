package org.demo.itextpdf7.advanced;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
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

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        String dest = "E:\\downloads\\ticket-form.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        doc.add(new Paragraph("XXX集团公开招聘").setFont(songFont)
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold());
        doc.add(new Paragraph("报名表").setFont(songFont)
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold());
        doc.add(createList());

        doc.close();
    }

    private static List createList() throws MalformedURLException {
        List list = new List()
                .setSymbolIndent(1)
                .setListSymbol("")
                .setFont(songFont);
        ListItem notification = new ListItem("考生须知");
        notification.setTextAlignment(TextAlignment.CENTER);
        Image image = new Image(ImageDataFactory.create("E:\\data\\photos\\edb08a90abab84f15c10e7a63f2b0c88.jpeg"));
        System.out.println("图片高" + image.getImageHeight());
        System.out.println("图片宽" + image.getImageWidth());
        image.scaleToFit(150, 105);
        ListItem imageItem = new ListItem(image);
        imageItem.setFixedPosition(430, 620, UnitValue.createPercentValue(100));
//        list.add("\u59d3\u540d\uff1a\u4e9a\u5386\u514b\u65af\u30fb\u725b\u903c")
        list.add("姓名：亚历克斯牛逼")
                .add("性别：女")
                .add("身份证号码：360703199901010022")
                .add("手机号码：13001010202")
                .add("报考岗位代码：GW0001")
                .add("报考岗位名称：AV演员")
                .add("准考证号：ZKZ0001")
                .add("考试地点：月球")
                .add("考场号：KC0001")
                .add("考试时间  2024-02-07 20:00:00 ~ 2024-02-29 23:59:59")
                .add(notification)
                .add("1.考生必须持准考证和有效身份证件(身份证，军人、武警人员证件，护照等)入场，证件不全者不得入场。")
                .add("2.考生须在每场考试(含笔试和面试)开考前30分钟入场。开始考试30分钟以后，考生不得入场。")
                .add("3.考生只准携带必要的文具入场，如2B铅笔(涂黑答题卡用)、签字笔、橡皮，禁止携带各种文字材料或通讯工具等。考场内不得擅自相互借用文具。")
                .add("4.考试正式开始后，考生不得中途退场。")
                .add("5.考生在考场内须遵守考场纪律，服从管理，如有考试违规行为，按相关规定进行理。")
                .add(imageItem);
        return list;
    }


}

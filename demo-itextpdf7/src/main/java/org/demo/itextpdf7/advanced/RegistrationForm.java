package org.demo.itextpdf7.advanced;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author lzs
 * @Date 2024/3/20 8:54
 **/
public class RegistrationForm {

    static PdfFont songFont;

    static {
        try {
            songFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        String dest = "E:\\downloads\\registration-form.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        doc.add(new Paragraph("报名表").setFont(songFont)
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold());
        doc.add(new Paragraph("应聘岗位：").setFont(songFont));
        //表格：基本情况
        doc.add(createBaseInfoTable());
        //表格：教育经历
        doc.add(createEducationTable());
        //表格：工作经历及业绩
        doc.add(createWorkExperienceTable());
        //表格：专业技能及资格认定
        doc.add(createTechnologyTable());
        //表格：奖惩情况
        doc.add(createAwardsTable());
        //表格：家庭情况及社会关系（直系亲属必填）
        doc.add(createFamilyTable());

        doc.close();
    }

    private static Table createFamilyTable() {
        Table table = new Table(UnitValue.createPercentArray(new float[]{20, 20, 20, 20, 20})).useAllAvailableWidth();
        table.setFont(songFont);
        Cell cell = new Cell(1, 5).add(new Paragraph("家庭情况及社会关系（直系亲属必填）"));
        cell.setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("姓名"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("与本人关系"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("出生年月"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("工作单位"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("职务/岗位"));
        table.addCell(cell);
        for (int i = 0; i < 25; i++) {
            cell = new Cell().add(new Paragraph("")).setHeight(20);
            table.addCell(cell);
        }
        return table;
    }

    private static Table createAwardsTable() {
        Table table = new Table(UnitValue.createPercentArray(new float[]{20, 20, 20, 20, 20})).useAllAvailableWidth();
        table.setFont(songFont);
        Cell cell = new Cell(1, 5).add(new Paragraph("奖惩情况"));
        cell.setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("时间"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("内容"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("个人/集体"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("颁发单位"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("具体原因"));
        table.addCell(cell);
        for (int i = 0; i < 20; i++) {
            cell = new Cell().add(new Paragraph("")).setHeight(20);
            table.addCell(cell);
        }
        return table;
    }

    private static Table createTechnologyTable() {
        Table table = new Table(UnitValue.createPercentArray(new float[]{20, 16, 16, 16, 16, 16})).useAllAvailableWidth();
        table.setFont(songFont);
        Cell cell = new Cell(1, 6).add(new Paragraph("专业技能及资格认定"));
        cell.setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("系列"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("职称"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("专业"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("职称等级"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("授予单位"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("评定时间"));
        table.addCell(cell);
        for (int i = 0; i < 12; i++) {
            cell = new Cell().add(new Paragraph("")).setHeight(20);
            table.addCell(cell);
        }
        return table;
    }

    private static Table createWorkExperienceTable() {
        Table table = new Table(UnitValue.createPercentArray(new float[]{25, 25, 25, 25})).useAllAvailableWidth();
        table.setFont(songFont);
        Cell cell = new Cell(1, 4).add(new Paragraph("工作经历及业绩"));
        cell.setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("起止年月"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("工作单位"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("职务/岗位"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("证明人"));
        table.addCell(cell);
        for (int i = 0; i < 8; i++) {
            cell = new Cell().add(new Paragraph("")).setHeight(20);
            table.addCell(cell);
        }
        cell = new Cell().add(new Paragraph("主要工作业绩")).setHeight(20 * 3);
        cell.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        cell = new Cell(1, 3).add(new Paragraph("")).setHeight(20 * 3);
        table.addCell(cell);
        return table;
    }

    private static Table createEducationTable() {
        Table table = new Table(UnitValue.createPercentArray(new float[]{20, 20, 20, 20, 20})).useAllAvailableWidth();
        table.setFont(songFont);
        Cell cell = new Cell(1, 5).add(new Paragraph("教育经历"));
        cell.setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("起止年月"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("毕业院校"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("所学专业"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("学历"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("学位"));
        table.addCell(cell);
        for (int i = 0; i < 15; i++) {
            cell = new Cell().add(new Paragraph("")).setHeight(20);
            table.addCell(cell);
        }
        return table;
    }

    private static Table createBaseInfoTable() throws MalformedURLException {
        Table table = new Table(UnitValue.createPercentArray(new float[]{13, 15, 13, 15, 13, 15, 16})).useAllAvailableWidth();
        table.setFont(songFont);
        Cell cell = new Cell(1, 7).add(new Paragraph("基本情况"));
        cell.setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER);
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("姓名"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("性别"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("民族"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        Image image = new Image(ImageDataFactory.create("E:\\data\\photos\\edb08a90abab84f15c10e7a63f2b0c88.jpeg"));
        image.setWidth(UnitValue.createPercentValue(100));
        Cell cell23 = new Cell(5, 1).add(image);
        cell23.setBorder(Border.NO_BORDER);
        table.addCell(cell23);
        cell = new Cell().add(new Paragraph("出生年月"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("籍贯"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("健康状态"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("身高（cm）"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("体重（kg）"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("政治面貌"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("外语（语种）")).setFontSize(10f);
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("等级"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("取得时间"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("现工作单位及职务"));
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("专业技术职称"));
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("户口所在地"));
        table.addCell(cell);
        cell = new Cell(1, 3).add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("身份证号码"));
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("最高学历及学位"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("毕业时间"));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("毕业学校及专业"));
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("联系电话"));
        table.addCell(cell);
        cell = new Cell(1, 3).add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("电子邮箱"));
        table.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph(""));
        table.addCell(cell);
        cell = new Cell().add(new Paragraph("通讯地址"));
        table.addCell(cell);
        cell = new Cell(1, 6).add(new Paragraph(""));
        table.addCell(cell);
        return table;
    }

}

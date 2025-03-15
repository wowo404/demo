package org.demo.itextpdf7.chapter03;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author lzs
 * @Date 2024/3/16 17:41
 **/
public class NewYorkTimes {

    public static void main(String[] args) throws IOException {
        String dest = "E:\\downloads\\new-york-times.pdf";
        String appleImg = "demo-itextpdf7/src/main/resources/img/ny_times_apple.jpg";
        String appleTxt = "demo-itextpdf7/src/main/resources/data/ny_times_apple.txt";
        String facebookImg = "demo-itextpdf7/src/main/resources/img/ny_times_fb.jpg";
        String facebookTxt = "demo-itextpdf7/src/main/resources/data/ny_times_fb.txt";
        String instImg = "demo-itextpdf7/src/main/resources/img/ny_times_inst.jpg";
        String instText = "demo-itextpdf7/src/main/resources/data/ny_times_inst.txt";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PageSize ps = PageSize.A5;
        Document document = new Document(pdf, ps);

//Set column parameters
        float offSet = 36;
        float columnWidth = (ps.getWidth() - offSet * 2 + 10) / 3;
        float columnHeight = ps.getHeight() - offSet * 2;

//Define column areas
        Rectangle[] columns = {
                new Rectangle(offSet - 5, offSet, columnWidth, columnHeight),
                new Rectangle(offSet + columnWidth, offSet, columnWidth, columnHeight),
                new Rectangle(
                        offSet + columnWidth * 2 + 5, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns));

// adding content
        Image apple = new Image(ImageDataFactory.create(appleImg)).setWidth(columnWidth);
        String articleApple = new String(Files.readAllBytes(Paths.get(appleTxt)), StandardCharsets.UTF_8);
        addArticle(document, "Apple Encryption Engineers, if Ordered to Unlock iPhone, Might Resist",
                "By JOHN MARKOFF MARCH 18, 2016", apple, articleApple);
        Image facebook = new Image(ImageDataFactory.create(facebookImg)).setWidth(columnWidth);
        String articleFB = new String(Files.readAllBytes(Paths.get(facebookTxt)), StandardCharsets.UTF_8);
        addArticle(document, "With \"Smog Jog\" Through Beijing, Zuckerberg Stirs Debate on Air Pollution",
                "By PAUL MOZUR MARCH 18, 2016", facebook, articleFB);
        Image inst = new Image(ImageDataFactory.create(instImg)).setWidth(columnWidth);
        String articleInstagram = new String(
                Files.readAllBytes(Paths.get(instText)), StandardCharsets.UTF_8);

        // The method addArticle is defined in the full  NewYorkTimes sample
        addArticle(document,
                "Instagram May Change Your Feed, Personalizing It With an Algorithm",
                "By MIKE ISAAC MARCH 15, 2016", inst, articleInstagram);

        document.close();
    }

    public static void addArticle(
            Document doc, String title, String author, Image img, String text)
            throws IOException {
        PdfFont timesNewRomanBold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        PdfFont timesNewRoman = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        Paragraph p1 = new Paragraph(title)
                .setFont(timesNewRomanBold)
                .setFontSize(14);
        doc.add(p1);
        doc.add(img);
        Paragraph p2 = new Paragraph()
                .setFont(timesNewRoman)
                .setFontSize(7)
                .setFontColor(Color.GRAY)
                .add(author);
        doc.add(p2);
        Paragraph p3 = new Paragraph()
                .setFont(timesNewRoman)
                .setFontSize(10)
                .add(text);
        doc.add(p3);
    }
}

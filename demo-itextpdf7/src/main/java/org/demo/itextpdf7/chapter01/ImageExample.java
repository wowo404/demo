package org.demo.itextpdf7.chapter01;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

/**
 * @author lzs
 * @Date 2024/3/16 11:16
 **/
public class ImageExample {

    public static void main(String[] args) throws IOException {
        String dest = "E:\\downloads\\image-example.pdf";
        String img1 = "demo-itextpdf7/src/main/resources/img/spring-logo2.png";
        String img2 = "demo-itextpdf7/src/main/resources/img/砍一刀.jpg";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Image fox = new Image(ImageDataFactory.create(img1));
        Image dog = new Image(ImageDataFactory.create(img2));
        Paragraph p = new Paragraph("The quick brown ")
                .add(fox)
                .add(" jumps over the lazy ")
                .add(dog);
        document.add(p);
        document.close();
    }

}

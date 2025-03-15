package org.demo.itextpdf7.chapter01;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

/**
 * @author lzs
 * @Date 2024/3/16 10:52
 **/
public class HelloWorld {

    public static void main(String[] args) throws IOException {
        String dest = "E:\\downloads\\hello-world.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World!"));
        document.close();
    }

}

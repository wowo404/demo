package org.demo.itextpdf7.chapter04;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

/**
 * @author lzs
 * @Date 2024/3/18 15:33
 **/
public class LinkAnnotation {

    public static void main(String[] args) throws FileNotFoundException {
        String dest = "E:\\downloads\\link-annotation.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);

        PdfLinkAnnotation annotation = new PdfLinkAnnotation(new Rectangle(0, 0))
                .setAction(PdfAction.createURI("https://itextpdf.com/"));
        Link link = new Link("here", annotation);
        Paragraph p = new Paragraph("The example of link annotation. Click ")
                .add(link.setUnderline())
                .add(" to learn more...");
        document.add(p);
        document.close();
    }

}

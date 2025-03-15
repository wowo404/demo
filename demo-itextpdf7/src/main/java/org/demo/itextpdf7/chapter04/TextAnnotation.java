package org.demo.itextpdf7.chapter04;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

/**
 * @author lzs
 * @Date 2024/3/18 15:24
 **/
public class TextAnnotation {

    public static void main(String[] args) throws FileNotFoundException {
        String dest = "E:\\downloads\\text-annotation.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        //add text annotation
        Paragraph paragraph = new Paragraph("The example of text annotation.");
        document.add(paragraph);

        PdfAnnotation ann = new PdfTextAnnotation(new Rectangle(20, 800, 0, 0))
                .setColor(Color.GREEN)
                .setTitle(new PdfString("iText"))
                .setContents("With iText, "
                        + "you can truly take your documentation needs to the next level.")
                .setOpen(true);
        pdf.getFirstPage().addAnnotation(ann);

        document.close();
    }

}

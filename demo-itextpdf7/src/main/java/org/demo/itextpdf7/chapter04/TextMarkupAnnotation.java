package org.demo.itextpdf7.chapter04;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextMarkupAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

/**
 * @author lzs
 * @Date 2024/3/18 15:42
 **/
public class TextMarkupAnnotation {

    public static void main(String[] args) throws FileNotFoundException {
        String dest = "E:\\downloads\\text-markup-annotation.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf);
        //add text annotation
        Paragraph paragraph = new Paragraph("The example of text markup annotation.");
        document.add(paragraph);
        PdfAnnotation ann = PdfTextMarkupAnnotation.createHighLight(
                new Rectangle(105, 790, 64, 10),
                new float[]{169, 790, 105, 790, 169, 800, 105, 800})
                .setColor(Color.YELLOW)
                .setTitle(new PdfString("Hello!"))
                .setContents(new PdfString("I'm a popup."))
                .setTitle(new PdfString("iText"))
                .setOpen(true)
                .setRectangle(new PdfArray(new float[]{100, 600, 200, 100}));
        pdf.getFirstPage().addAnnotation(ann);
        document.close();
    }

}

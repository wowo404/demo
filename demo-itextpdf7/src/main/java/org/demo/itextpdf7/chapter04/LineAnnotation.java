package org.demo.itextpdf7.chapter04;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLineAnnotation;

import java.io.FileNotFoundException;

/**
 * @author lzs
 * @Date 2024/3/18 15:39
 **/
public class LineAnnotation {

    public static void main(String[] args) throws FileNotFoundException {
        String dest = "E:\\downloads\\line-annotation.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PdfPage page = pdf.addNewPage();
        PdfArray lineEndings = new PdfArray();
        lineEndings.add(new PdfName("Diamond"));
        lineEndings.add(new PdfName("Diamond"));
        PdfAnnotation annotation = new PdfLineAnnotation(
                new Rectangle(0, 0),
                new float[]{20, 790, page.getPageSize().getWidth() - 20, 790})
                .setLineEndingStyles((lineEndings))
                .setContentsAsCaption(true)
                .setTitle(new PdfString("iText"))
                .setContents("The example of line annotation")
                .setColor(Color.BLUE);
        page.addAnnotation(annotation);
        pdf.close();
    }

}

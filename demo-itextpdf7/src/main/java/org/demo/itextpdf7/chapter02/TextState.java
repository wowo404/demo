package org.demo.itextpdf7.chapter02;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzs
 * @Date 2024/3/16 15:53
 **/
public class TextState {

    public static void main(String[] args) throws IOException {
        String dest = "E:\\downloads\\text-state-example.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PageSize ps = PageSize.A4;
        PdfPage page = pdf.addNewPage(ps);
        PdfCanvas canvas = new PdfCanvas(page);
        List<String> text = getText();
        canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight());
        canvas.beginText()
                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD), 14)
                .setLeading(14 * 1.2f)
                .moveText(70, -40);
        for (String s : text) {
            //Add text and move to the next line
            canvas.newlineShowText(s);
        }
        canvas.endText();
        pdf.close();
    }

    public static List<String> getText() {
        List<String> text = new ArrayList<>();
        text.add("         Episode V         ");
        text.add("  THE EMPIRE STRIKES BACK  ");
        text.add("It is a dark time for the");
        text.add("Rebellion. Although the Death");
        text.add("Star has been destroyed,");
        text.add("Imperial troops have driven the");
        text.add("Rebel forces from their hidden");
        text.add("base and pursued them across");
        text.add("the galaxy.");
        text.add("Evading the dreaded Imperial");
        text.add("Starfleet, a group of freedom");
        text.add("fighters led by Luke Skywalker");
        text.add("has established a new secret");
        text.add("base on the remote ice world");
        text.add("of Hoth...");
        return text;
    }

}

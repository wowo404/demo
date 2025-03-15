package org.demo.itextpdf7.chapter02;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.IOException;
import java.util.List;

/**
 * @author lzs
 * @Date 2024/3/16 16:48
 **/
public class StarWarsCrawl {

    public static void main(String[] args) throws IOException {
        String dest = "E:\\downloads\\star-wars-crawl.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PageSize ps = PageSize.A4;
        PdfPage page = pdf.addNewPage(ps);
        PdfCanvas canvas = new PdfCanvas(page);
        List<String> text = TextState.getText();
        int maxStringWidth = 0;
        for (String fragment : text) {
            if (fragment.length() > maxStringWidth) maxStringWidth = fragment.length();
        }
        canvas.rectangle(0, 0, ps.getWidth(), ps.getHeight())
                .setColor(Color.BLACK, true)
                .fill();
        canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight());
        Color yellowColor = new DeviceCmyk(0.f, 0.0537f, 0.769f, 0.051f);
        float lineHeight = 5;
        float yOffset = -40;
        canvas.beginText()
                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD), 1)
                .setColor(yellowColor, true);
        for (int j = 0; j < text.size(); j++) {
            String line = text.get(j);
            float xOffset = ps.getWidth() / 2 - 45 - 8 * j;
            float fontSizeCoeff = 6 + j;
            float lineSpacing = (lineHeight + j) * j / 1.5f;
            int stringWidth = line.length();
            for (int i = 0; i < stringWidth; i++) {
                float angle = ((float) maxStringWidth / 2 - i) / 2f;
                float charXOffset = (4 + (float) j / 2) * i;
                canvas.setTextMatrix(fontSizeCoeff, 0,
                        angle, fontSizeCoeff / 1.5f,
                        xOffset + charXOffset, yOffset - lineSpacing)
                        .showText(String.valueOf(line.charAt(i)));
            }
        }
        canvas.endText();
        pdf.close();
    }

}

package org.demo.itextpdf7.advanced;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author lzs
 * @Date 2024/4/28 17:00
 **/
public class DeskLabelForm {

    static PdfFont songFont;

    static {
        try {
            songFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String dest = "E:\\downloads\\desk-label-form.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        doc.add(new Paragraph("XXX集团公开招聘笔试考生桌贴（001考场）").setFont(songFont)
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold());
        doc.add(createTable());

        doc.close();
    }

    private static Table createTable() {
        Table table = new Table(UnitValue.createPointArray(new float[]{33f, 33f, 34f})).useAllAvailableWidth();
        table.setFont(songFont).setBorder(Border.NO_BORDER).setFixedLayout();
        for (int i = 0; i < 30; i++) {
            Cell cell = createListCell();
            table.addCell(cell);
        }
        return table;
    }

    private static Cell createListCell() {
        List list = new List()
                .setSymbolIndent(3f)
                .setListSymbol("")
                .setFont(songFont)
                .setFontSize(8f)
                .setBorder(new SolidBorder(0.5f));
        list.add("考场：         第1考场")
                .add("座位号：     1")
                .add("准考证号：56325452")
                .add("报考单位：南康人才集团")
                .add("报考岗位：牛逼供测试");
        return new Cell().add(list).setBorder(Border.NO_BORDER);
    }

}

package org.demo.itextpdf7.advanced;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;

/**
 * @author lzs
 * @Date 2024/4/26 17:50
 **/
public class TableMargin {
    public static final String DEST = "E:\\downloads\\indent_table.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new TableMargin().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        PdfCanvas cb = new PdfCanvas(pdfDoc.addNewPage());
        cb.moveTo(36, 842);
        cb.lineTo(36, 0);
        cb.stroke();

        Table table = new Table(UnitValue.createPointArray(new float[]{12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f, 12.5f}));
        table.setHorizontalAlignment(HorizontalAlignment.LEFT);
        table.setWidth(150);

        for (int aw = 0; aw < 16; aw++) {
            table.addCell(new Cell().add(new Paragraph("hi")));
        }

        table.setMarginLeft(25);

        doc.add(table);

        doc.close();
    }
}

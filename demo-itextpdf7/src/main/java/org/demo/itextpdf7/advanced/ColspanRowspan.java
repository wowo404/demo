package org.demo.itextpdf7.advanced;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;

/**
 * @author lzs
 * @Date 2024/3/19 18:29
 **/
public class ColspanRowspan {
    public static final String DEST = "E:\\downloads\\colspan_rowspan.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new ColspanRowspan().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        Table table = new Table(UnitValue.createPercentArray(new float[]{25,25,25,25})).useAllAvailableWidth();
        Cell cell = new Cell().add(new Paragraph(" 1,1 "));
        table.addCell(cell);

        cell = new Cell().add(new Paragraph(" 1,2 "));
        table.addCell(cell);

        Cell cell23 = new Cell(2, 2).add(new Paragraph("multi 1,3 and 1,4"));
        table.addCell(cell23);

        cell = new Cell().add(new Paragraph(" 2,1 "));
        table.addCell(cell);

        cell = new Cell().add(new Paragraph(" 2,2 "));
        table.addCell(cell);

        doc.add(table);

        doc.close();
    }
}

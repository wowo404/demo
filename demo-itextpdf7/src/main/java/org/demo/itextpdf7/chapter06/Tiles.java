package org.demo.itextpdf7.chapter06;

import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import java.io.IOException;

/**
 * @author lzs
 * @Date 2024/3/19 15:20
 **/
public class Tiles {

    public static void main(String[] args) throws IOException {
        String src = "demo-itextpdf7/src/main/resources/pdf/the_golden_gate_bridge.pdf";
        String dest = "E:\\downloads\\tiles.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PdfDocument sourcePdf = new PdfDocument(new PdfReader(src));
        PdfPage origPage = sourcePdf.getPage(1);
        PdfFormXObject pageCopy = origPage.copyAsFormXObject(pdf);
        Rectangle orig = origPage.getPageSize();
//Tile size
        Rectangle tileSize = PageSize.A4.rotate();
        AffineTransform transformationMatrix = AffineTransform.getScaleInstance(
                tileSize.getWidth() / orig.getWidth() * 2f,
                tileSize.getHeight() / orig.getHeight() * 2f);
//The first tile
        PdfPage page = pdf.addNewPage(PageSize.A4.rotate());
        PdfCanvas canvas = new PdfCanvas(page);
        canvas.concatMatrix(transformationMatrix);
        canvas.addXObject(pageCopy, 0, -orig.getHeight() / 2f);
//The second tile
        page = pdf.addNewPage(PageSize.A4.rotate());
        canvas = new PdfCanvas(page);
        canvas.concatMatrix(transformationMatrix);
        canvas.addXObject(pageCopy, -orig.getWidth() / 2f, -orig.getHeight() / 2f);
//The third tile
        page = pdf.addNewPage(PageSize.A4.rotate());
        canvas = new PdfCanvas(page);
        canvas.concatMatrix(transformationMatrix);
        canvas.addXObject(pageCopy, 0, 0);
//The fourth tile
        page = pdf.addNewPage(PageSize.A4.rotate());
        canvas = new PdfCanvas(page);
        canvas.concatMatrix(transformationMatrix);
        canvas.addXObject(pageCopy, -orig.getWidth() / 2f, 0);
// closing the documents
        pdf.close();
        sourcePdf.close();
    }

}

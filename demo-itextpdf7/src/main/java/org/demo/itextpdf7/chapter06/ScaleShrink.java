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
 * @Date 2024/3/19 14:05
 **/
public class ScaleShrink {

    public static void main(String[] args) throws IOException {
        String src = "demo-itextpdf7/src/main/resources/pdf/the_golden_gate_bridge.pdf";
        String dest = "E:\\downloads\\scale-shrink.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PdfDocument origPdf = new PdfDocument(new PdfReader(src));
        PdfPage origPage = origPdf.getPage(1);
        Rectangle orig = origPage.getPageSizeWithRotation();

//Add A4 page
        PdfPage page = pdf.addNewPage(PageSize.A4.rotate());
//Shrink original page content using transformation matrix
        PdfCanvas canvas = new PdfCanvas(page);
        AffineTransform transformationMatrix = AffineTransform.getScaleInstance(
                page.getPageSize().getWidth() / orig.getWidth(),
                page.getPageSize().getHeight() / orig.getHeight());
        canvas.concatMatrix(transformationMatrix);
        PdfFormXObject pageCopy = origPage.copyAsFormXObject(pdf);
        canvas.addXObject(pageCopy, 0, 0);

//Add page with original size
        pdf.addPage(origPage.copyTo(pdf));

//Add A2 page
        page = pdf.addNewPage(PageSize.A2.rotate());
//Scale original page content using transformation matrix
        canvas = new PdfCanvas(page);
        transformationMatrix = AffineTransform.getScaleInstance(
                page.getPageSize().getWidth() / orig.getWidth(),
                page.getPageSize().getHeight() / orig.getHeight());
        canvas.concatMatrix(transformationMatrix);
        canvas.addXObject(pageCopy, 0, 0);

        pdf.close();
        origPdf.close();
    }

}

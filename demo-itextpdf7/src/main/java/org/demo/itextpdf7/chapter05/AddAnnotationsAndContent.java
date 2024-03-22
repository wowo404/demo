package org.demo.itextpdf7.chapter05;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.IOException;

/**
 * @author lzs
 * @Date 2024/3/18 16:57
 **/
public class AddAnnotationsAndContent {

    public static void main(String[] args) throws IOException {
        String src = "E:\\downloads\\create-form.pdf";
        String dest = "E:\\downloads\\add-annotations-and-content.pdf";
        PdfDocument pdfDoc =
                new PdfDocument(new PdfReader(src), new PdfWriter(dest));
// add annotation
        PdfAnnotation ann = new PdfTextAnnotation(new Rectangle(400, 795, 0, 0))
                .setTitle(new PdfString("iText"))
                .setContents("Please, fill out the form.")
                .setOpen(true);
        pdfDoc.getFirstPage().addAnnotation(ann);
        //add text
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        canvas.beginText().setFontAndSize(
                PdfFontFactory.createFont(FontConstants.HELVETICA), 12)
                .moveText(265, 597)
                .showText("I agree to the terms and conditions.")
                .endText();
        //add field
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        PdfButtonFormField checkField = PdfFormField.createCheckBox(
                pdfDoc, new Rectangle(245, 594, 15, 15),
                "agreement", "Off", PdfFormField.TYPE_CHECK);
        checkField.setRequired(true);
        form.addField(checkField);
        //update reset button
        form.getField("reset").setAction(PdfAction.createResetForm(
                new String[]{"name", "language", "experience1", "experience2",
                        "experience3", "shift", "info", "agreement"}, 0));

        pdfDoc.close();
    }

}

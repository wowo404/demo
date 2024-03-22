package org.demo.itextpdf7.chapter04;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfChoiceFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;

/**
 * @author lzs
 * @Date 2024/3/18 17:00
 **/
public class CreateForm {

    public static void main(String[] args) throws FileNotFoundException {
        String dest = "E:\\downloads\\create-form.pdf";
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PageSize ps = PageSize.A4;
        Document doc = new Document(pdf, ps);
        //add paragraph
        Paragraph title = new Paragraph("Application for employment")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(16);
        doc.add(title);
        doc.add(new Paragraph("Full name:").setFontSize(12));
        doc.add(new Paragraph("Native language:      English         French       German        Russian        Spanish").setFontSize(12));
        doc.add(new Paragraph("Experience in:       cooking        driving           software development").setFontSize(12));
        doc.add(new Paragraph("Preferred working shift:").setFontSize(12));
        doc.add(new Paragraph("Additional information:").setFontSize(12));
        // add form
        PdfAcroForm form = PdfAcroForm.getAcroForm(doc.getPdfDocument(), true);
        //text field
        PdfTextFormField nameField = PdfTextFormField.createText(
                doc.getPdfDocument(), new Rectangle(99, 753, 425, 15), "name", "");
        form.addField(nameField);
        //radio buttons
        PdfButtonFormField group = PdfFormField.createRadioGroup(
                doc.getPdfDocument(), "language", "");
        PdfFormField.createRadioButton(doc.getPdfDocument(),
                new Rectangle(130, 728, 15, 15), group, "English");
        PdfFormField.createRadioButton(doc.getPdfDocument(),
                new Rectangle(200, 728, 15, 15), group, "French");
        PdfFormField.createRadioButton(doc.getPdfDocument(),
                new Rectangle(260, 728, 15, 15), group, "German");
        PdfFormField.createRadioButton(doc.getPdfDocument(),
                new Rectangle(330, 728, 15, 15), group, "Russian");
        PdfFormField.createRadioButton(doc.getPdfDocument(),
                new Rectangle(400, 728, 15, 15), group, "Spanish");
        form.addField(group);
        //check boxes
        for (int i = 0; i < 3; i++) {
            PdfButtonFormField checkField = PdfFormField.createCheckBox(
                    doc.getPdfDocument(), new Rectangle(119 + i * 69, 701, 15, 15),
                    "experience".concat(String.valueOf(i + 1)), "Off",
                    PdfFormField.TYPE_CHECK);
            form.addField(checkField);
        }
        //choice field
        String[] options = {"Any", "6.30 am - 2.30 pm", "1.30 pm - 9.30 pm"};
        PdfChoiceFormField choiceField = PdfFormField.createComboBox(
                doc.getPdfDocument(), new Rectangle(163, 676, 115, 15),
                "shift", "Any", options);
        form.addField(choiceField);
        //multi-line field
        PdfTextFormField infoField = PdfTextFormField.createMultilineText(
                doc.getPdfDocument(), new Rectangle(158, 625, 366, 40), "info", "");
        form.addField(infoField);
        //reset button
        PdfButtonFormField button = PdfFormField.createPushButton(doc.getPdfDocument(),
                new Rectangle(479, 594, 45, 15), "reset", "RESET");
        button.setAction(PdfAction.createResetForm(
                new String[]{"name", "language", "experience1", "experience2",
                        "experience3", "shift", "info"}, 0));
        form.addField(button);

        doc.close();
    }

}

package org.liu.demo.pdf;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * pdf reader
 *
 * @author liuzhangsheng
 * @create 2018/9/3
 */
public class PDFDemo {

    public static void main(String[] args) throws IOException {
        getText();
    }

    public static String getText() throws IOException {
        PdfReader pdfReader = new PdfReader("/Users/liuzhangsheng/Documents/work/tnjc/e签宝/E钱宝对接资料/TSignDemo/pdf/contract_tpl_magic.pdf");
        System.out.println(pdfReader.getInfo());
        AcroFields acroFields = pdfReader.getAcroFields();
        ArrayList blankSignatureNames = acroFields.getBlankSignatureNames();
        for (Object blankSignatureName : blankSignatureNames) {
            System.out.println(blankSignatureName);
        }
        System.out.println();
        HashMap fields = acroFields.getFields();
        Set set = fields.entrySet();
        for (Object o : set) {

        }
        System.out.println(pdfReader.getAcroFields());
        System.out.println(pdfReader.getAcroForm());
        System.out.println(pdfReader.getCatalog());
        System.out.println(pdfReader.getEofPos());
        System.out.println(pdfReader.getCryptoMode());
        System.out.println(pdfReader.getCertificationLevel());
        System.out.println(pdfReader.getCryptoMode());
        System.out.println(pdfReader.getFileLength());
        System.out.println(pdfReader.getJavaScript());
        System.out.println(pdfReader.getLastXref());
        System.out.println(pdfReader.getMetadata());
        System.out.println(pdfReader.getNamedDestination());
        System.out.println(pdfReader.getNamedDestinationFromNames());
        System.out.println(pdfReader.getNamedDestinationFromStrings());

        return null;
    }

}

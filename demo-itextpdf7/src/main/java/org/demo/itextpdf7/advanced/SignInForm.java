package org.demo.itextpdf7.advanced;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
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
public class SignInForm {

    static PdfFont songFont;

    static {
        try {
            songFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String dest = "E:\\downloads\\sign-in-form.pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        doc.add(new Paragraph("XXX集团公开招聘笔试签到表（001考场）").setFont(songFont)
                .setTextAlignment(TextAlignment.CENTER).setFontSize(14f).setBold());
        doc.add(createTable());

        doc.close();
    }

    private static Table createTable() {
        Table table = new Table(UnitValue.createPointArray(new float[]{16f, 17f, 16f, 17f, 16f, 17f})).useAllAvailableWidth();
        table.setFont(songFont).setBorder(Border.NO_BORDER).setFixedLayout();
        for (int i = 0; i < 30; i++) {
            Cell cell = createImageCell();
            table.addCell(cell);
            cell = createInfoCell();
            table.addCell(cell);
        }
        return table;
    }

    private static Cell createInfoCell() {
        List list = new List()
                .setSymbolIndent(1f)
                .setListSymbol("")
                .setFont(songFont)
                .setFontSize(8f);
        ListItem sign = new ListItem("考生签名 _______");
        sign.setMarginTop(6f);
        sign.setMarginBottom(6f);
        list.add("岗位名称")
                .add(sign)
                .add("360782198802010222");
        return new Cell().add(list).setBorder(Border.NO_BORDER);
    }

    private static Cell createImageCell() {
        //照片
        Image image;
        try {
            image = new Image(ImageDataFactory.create("E:\\data\\photos\\商品图2.0\\spring-logo3.png"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("生成报名表pdf时，读取照片失败");
        }
//        image.setWidth(UnitValue.createPercentValue(100));
//        image.setMaxHeight(80);
        image.scaleToFit(80, 65);
        return new Cell().add(image).setBorder(Border.NO_BORDER);
    }

}

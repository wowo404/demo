package cn.seaway.word2007;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * 读取word2007
 */
public class ReadWord2007 {

    public static void main(String[] args) throws Exception {
        read();
    }

    /**
     * 使用XPath读取word2007
     * WARNING:读取失败
     */
    public static void readWord2007ByXPath() throws Exception {
        String filePath = "E:\\work\\minxun\\经济普查\\宁都县可视化(1).docx"; // 替换为实际文件路径
        FileInputStream fis = new FileInputStream(filePath);
        OPCPackage pkg = OPCPackage.open(fis);
        XWPFDocument document = new XWPFDocument(pkg);

        XmlCursor cursor = document.getDocument().newCursor();
        cursor.toStartDoc();

        // 使用XPath选择所有的文本内容
        cursor.selectPath(".//w1:t");

        while (cursor.toNextSelection()) {
            // 获取当前选择的文本内容
            String text = cursor.getTextValue();
            if (text != null) {
                System.out.println(text);
            }
        }
//        cursor.toEndDoc();
        cursor.dispose();
        document.close();
        pkg.close();
        fis.close();
    }

    public static void readDoc2003() throws IOException {
        String path = "E:\\work\\minxun\\经济普查\\1宁都县可视化(1).doc";
        HWPFDocument document = new HWPFDocument(Files.newInputStream(Paths.get(path)));
        PicturesTable picturesTable = document.getPicturesTable();
        Range range = document.getRange();
        for (int i = 0; i < range.numParagraphs(); i++) {
            Paragraph paragraph = range.getParagraph(i);
//            System.out.println(paragraph.text());
            int numCharacterRuns = paragraph.numCharacterRuns();
            for (int j = 0; j < numCharacterRuns; j++) {
                CharacterRun characterRun = paragraph.getCharacterRun(j);
                if (picturesTable.hasPicture(characterRun)) {
                    Picture picture = picturesTable.extractPicture(characterRun, false);
                    System.out.println(picture.suggestFullFileName() + "- " + picture.suggestFileExtension());
                } else {
                    System.out.println(characterRun.text());
                }
            }
            if (paragraph.isInTable()) {
                Table table = range.getTable(paragraph);
                for (int j = 0; j < table.numRows(); j++) {
                    TableRow row = table.getRow(j);
                    for (int k = 0; k < row.numCells(); k++) {
                        TableCell cell = row.getCell(k);
                        System.out.print(cell.text());
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void readTitle() throws IOException {
        String path = "E:\\downloads\\test-title.docx";
        XWPFDocument document = new XWPFDocument(Files.newInputStream(Paths.get(path)));
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            String titleLvl = getTitleLvl(document, paragraph);
            System.out.println("paragraph text:" + text);
            System.out.println("titleLvl:" + titleLvl);
        }
    }

    /**
     * Word中的大纲级别，可以通过getPPr().getOutlineLvl()直接提取，但需要注意，Word中段落级别，通过如下三种方式定义：
     * 1、直接对段落进行定义；
     * 2、对段落的样式进行定义；
     * 3、对段落样式的基础样式进行定义。
     * 因此，在通过“getPPr().getOutlineLvl()”提取时，需要依次在如上三处读取。
     */
    private static String getTitleLvl(XWPFDocument doc, XWPFParagraph para) {
        String titleLvl = "";
        try {
            //判断该段落是否设置了大纲级别
            if (para.getCTP().getPPr().getOutlineLvl() != null) {
                return String.valueOf(para.getCTP().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {
        }
        try {
            //判断该段落的样式是否设置了大纲级别
            if (doc.getStyles().getStyle(para.getStyle()).getCTStyle().getPPr().getOutlineLvl() != null) {
                return String.valueOf(doc.getStyles().getStyle(para.getStyle()).getCTStyle().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {
        }
        try {
            //判断该段落的样式的基础样式是否设置了大纲级别
            if (doc.getStyles().getStyle(doc.getStyles().getStyle(para.getStyle()).getCTStyle().getBasedOn().getVal())
                    .getCTStyle().getPPr().getOutlineLvl() != null) {
                String styleName = doc.getStyles().getStyle(para.getStyle()).getCTStyle().getBasedOn().getVal();
                return String.valueOf(doc.getStyles().getStyle(styleName).getCTStyle().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {

        }
        try {
            if (para.getStyleID() != null) {
                return para.getStyleID();
            }
        } catch (Exception e) {

        }

        return titleLvl;
    }

    public static void read() throws IOException {
        String path = "E:\\work\\minxun\\经济普查\\农业\\0703 南康区.docx";
        XWPFDocument document = new XWPFDocument(Files.newInputStream(Paths.get(path)));
        Iterator<IBodyElement> iterator = document.getBodyElementsIterator();
        while (iterator.hasNext()) {
            IBodyElement element = iterator.next();
            if (element instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) element;
                String text = paragraph.getText();
                if (null != text && !text.trim().isEmpty()) {
                    System.out.println(text);
                } else {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        List<XWPFPicture> pictures = run.getEmbeddedPictures();
                        for (XWPFPicture picture : pictures) {
                            System.out.println(picture.getPictureData().getFileName());
                        }
                    }
                }
            } else if (element instanceof XWPFSDT) {
                XWPFSDT contentControl = (XWPFSDT) element;
                String text = contentControl.getContent().getText();
                System.out.println(text);
            } else if (element instanceof XWPFTable) {
                XWPFTable table = (XWPFTable) element;
                for (XWPFTableRow row : table.getRows()) {
                    StringBuilder sb = new StringBuilder();
                    for (XWPFTableCell cell : row.getTableCells()) {
                        sb.append(cell.getText());
                    }
                    System.out.println(sb);
                }
            }
        }
    }

    public static void readWord2007() throws IOException {
        String directory = "E:\\work\\minxun\\经济普查\\农业\\";
        String imageDirectory = directory + "南康区";
        createFolder(imageDirectory);
        String path = directory + "0703 南康区.docx";
        XWPFDocument document = new XWPFDocument(Files.newInputStream(Paths.get(path)));
        Iterator<IBodyElement> iterator = document.getBodyElementsIterator();
        BlockCodeIntroducesResp resp = null;
        while (iterator.hasNext()) {
            IBodyElement element = iterator.next();
            if (element instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) element;
                String text = paragraph.getText();
                if (null != text && !text.trim().isEmpty()) {
                    text = text.trim();
                    if (text.startsWith("===")) {
                        System.out.println(resp);
                        if (text.startsWith("===end")) {
                            return;
                        }
                        resp = new BlockCodeIntroducesResp();
                        String[] codeAndNature = text.replace("===", "").split(",");
                        resp.setName(codeAndNature[0]);
                        resp.setCode(codeAndNature[1]);
                        resp.setNature(Integer.parseInt(codeAndNature[2]));
                    } else {
                        if (null != resp) {
                            if (resp.getIntroduces().isEmpty()) {
                                BlockCodeIntroducesResp.Introduce introduce = new BlockCodeIntroducesResp.Introduce();
                                introduce.getContent().add(text);
                                resp.getIntroduces().add(introduce);
                            } else {
                                resp.getIntroduces().get(0).getContent().add(text);
                            }
                        }
                    }
                } else {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        List<XWPFPicture> pictures = run.getEmbeddedPictures();
                        int i = 0;
                        for (XWPFPicture picture : pictures) {
                            if (null != resp) {
                                String suffix = getFileSuffix(picture.getPictureData().getFileName());
                                String picName = resp.getName() + i + suffix;
                                if (resp.getIntroduces().isEmpty()) {
                                    BlockCodeIntroducesResp.Introduce introduce = new BlockCodeIntroducesResp.Introduce();
                                    introduce.getImgUrl().add(picName);
                                    resp.getIntroduces().add(introduce);
                                } else {
                                    int size = resp.getIntroduces().get(0).getImgUrl().size() + i;
                                    picName = resp.getName() + size + suffix;
                                    resp.getIntroduces().get(0).getImgUrl().add(picName);
                                }
                                //把图片放入到指定的文件夹中
                                File file = new File(imageDirectory + "\\" + picName);
                                try (FileOutputStream fos = new FileOutputStream(file)) {
                                    fos.write(picture.getPictureData().getData());
                                }
                            } else {
                                System.out.println("resp为空？？？");
                            }
                        }
                    }
                }
            }
            System.out.println("--------------------------");
        }
    }

    private static void createFolder(String folder) {
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}

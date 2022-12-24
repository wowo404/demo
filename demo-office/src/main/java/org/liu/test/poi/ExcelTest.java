package org.liu.test.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Optional;

/**
 * @Author lzs
 * @Date 2021/10/26 18:11
 * @Description 描述
 * @Version 1.0
 **/
public class ExcelTest {

    public static void main(String[] args) throws Exception {
        testSetFormulaAndComment();
    }

    /**
     * 设置公式和注释
     *
     * @throws IOException
     */
    public static void testSetFormulaAndComment() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row row = sheet.createRow(0);
        Cell cell0 = row.createCell(0, CellType.STRING);
        Cell cell1 = row.createCell(1, CellType.STRING);
        Cell cell2 = row.createCell(2, CellType.STRING);
        cell0.setCellValue(1);
        cell1.setCellValue(2);
        cell2.setCellValue(3);

        Cell cell3 = row.createCell(3, CellType.FORMULA);
        cell3.setCellFormula("A1+B1+C1");

        Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();
        Comment cellComment = drawingPatriarch.createCellComment(new XSSFClientAnchor(0,0,0,0,4,0,5,1));
        cellComment.setString(new XSSFRichTextString("这是计算公式"));
        cellComment.setVisible(true);
        cell3.setCellComment(cellComment);

        workbook.write(new FileOutputStream("D:\\download\\test-set-formula.xlsx"));
        workbook.close();
    }

    /**
     * 读取公式和单元格值，注释
     *
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static void testGetFormulaAndComment() throws IOException, InvalidFormatException {
        Workbook workbook = new XSSFWorkbook(new File("D:\\download\\test-set-formula.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(3);
        String cellFormula = cell.getCellFormula();
        System.out.println(cellFormula);
        System.out.println(cell.getCellType());
        if (null != cell.getCellComment()) {
            System.out.println(cell.getCellComment().getString());
        }

        //CellReference cellReference = new CellReference("A3");
        //Row row = sheet.getRow(cellReference.getRow());
        //Cell cell = row.getCell(cellReference.getCol());

        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        CellValue cellValue = formulaEvaluator.evaluate(cell);
        switch (cellValue.getCellType()) {
            case _NONE:
                System.out.println("_NONE是给poi内部使用的，不会发生");
                break;
            case BLANK:
                System.out.println("blank cell");
                break;
            case ERROR:
                System.out.println(cellValue.getErrorValue());
                break;
            case STRING:
                System.out.println(cellValue.getStringValue());
                break;
            case BOOLEAN:
                System.out.println(cellValue.getBooleanValue());
                break;
            case FORMULA://CELL_TYPE_FORMULA will never happen
                System.out.println("不会发生这种情况，因为此cellValue对象是由FormulaEvaluator执行计算后的得到的对象");
                break;
            case NUMERIC:
                System.out.println(cellValue.getNumberValue());
                break;
            default:
                System.out.println("default is nothing");
        }
    }

    /**
     * 写入图片
     *
     * @throws Exception
     */
    public static void testDrawImage() throws Exception {
        Workbook workbook = new XSSFWorkbook();
        //Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sheet1");
        sheet.setColumnWidth(1, 6000/*1/256th of a character width*/);

        Row row = sheet.createRow(0);
        row.setHeightInPoints(100);

        row = sheet.createRow(10);
        row.setHeightInPoints(50);

        Picture picture;

        //two cell anchor in the same cell (B1) used without resizing the picture
        picture = drawImageOnExcelSheet(sheet,
                1, 0, 1000/*1/256th of a character width*/, 10/*points*/,
                1, 0, 5000/*1/256th of a character width*/, 90/*points*/,
                "mikt1.png", Workbook.PICTURE_TYPE_PNG, false);

        //one cell anchor (B3) used with resizing the picture
        picture = drawImageOnExcelSheet(sheet,
                1, 2, 1000/*1/256th of a character width*/, 10/*points*/,
                0, 0, 0, 0,
                "mikt2.png", Workbook.PICTURE_TYPE_JPEG, true);

        //two cell anchor (B10 to B12) used without resizing the picture
        picture = drawImageOnExcelSheet(sheet,
                1, 9, 1000/*1/256th of a character width*/, 10/*points*/,
                1, 11, 5000/*1/256th of a character width*/, 10/*points*/,
                "mikt3.png", Workbook.PICTURE_TYPE_PNG, false);

        if (workbook instanceof XSSFWorkbook) {
            workbook.write(new FileOutputStream("image-sutpid.xlsx"));
        } else if (workbook instanceof HSSFWorkbook) {
            workbook.write(new FileOutputStream("image-sutpid.xls"));
        }
        workbook.close();

    }

    /**
     * 写入图片
     * https://www.coder.work/article/1831737
     */
    private static Picture drawImageOnExcelSheet(Sheet sheet,
                                                 int col1, int row1, int dx1/*1/256th of a character width*/, int dy1/*points*/,
                                                 int col2, int row2, int dx2/*1/256th of a character width*/, int dy2/*points*/,
                                                 String pictureurl, int picturetype, boolean resize) throws Exception {

        int DEFAULT_COL_WIDTH = 10 * 256;
        float DEFAULT_ROW_HEIGHT = 12.75f;

        Row row = sheet.getRow(row1);
        float rowheight1 = (row != null) ? row.getHeightInPoints() : DEFAULT_ROW_HEIGHT;
        row = sheet.getRow(row2);
        float rowheight2 = (row != null) ? row.getHeightInPoints() : DEFAULT_ROW_HEIGHT;

        int colwidth1 = sheet.getColumnWidth(col1);
        int colwidth2 = sheet.getColumnWidth(col2);

        InputStream is = new FileInputStream(pictureurl);
        byte[] bytes = IOUtils.toByteArray(is);
        int pictureIdx = sheet.getWorkbook().addPicture(bytes, picturetype);
        is.close();

        CreationHelper helper = sheet.getWorkbook().getCreationHelper();

        Drawing drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);

        anchor.setRow1(row1); //first anchor determines upper left position
        if (sheet instanceof XSSFSheet) {
            anchor.setDy1(dy1 * Units.EMU_PER_POINT);
        } else if (sheet instanceof HSSFSheet) {
            anchor.setDy1((int) Math.round(dy1 * Units.PIXEL_DPI / Units.POINT_DPI * 14.75 * DEFAULT_ROW_HEIGHT / rowheight1));
        }
        anchor.setCol1(col1);
        if (sheet instanceof XSSFSheet) {
            anchor.setDx1((int) Math.round(dx1 * Units.EMU_PER_PIXEL * Units.DEFAULT_CHARACTER_WIDTH / 256f));
        } else if (sheet instanceof HSSFSheet) {
            anchor.setDx1((int) Math.round(dx1 * Units.DEFAULT_CHARACTER_WIDTH / 256f * 14.75 * DEFAULT_COL_WIDTH / colwidth1));
        }

        if (!resize) {
            anchor.setRow2(row2); //second anchor determines bottom right position
            if (sheet instanceof XSSFSheet) {
                anchor.setDy2(dy2 * Units.EMU_PER_POINT);
            } else if (sheet instanceof HSSFSheet) {
                anchor.setDy2((int) Math.round(dy2 * Units.PIXEL_DPI / Units.POINT_DPI * 14.75 * DEFAULT_ROW_HEIGHT / rowheight2));
            }
            anchor.setCol2(col2);
            if (sheet instanceof XSSFSheet) {
                anchor.setDx2((int) Math.round(dx2 * Units.EMU_PER_PIXEL * Units.DEFAULT_CHARACTER_WIDTH / 256f));
            } else if (sheet instanceof HSSFSheet) {
                anchor.setDx2((int) Math.round(dx2 * Units.DEFAULT_CHARACTER_WIDTH / 256f * 14.75 * DEFAULT_COL_WIDTH / colwidth2));
            }
        }

        Picture picture = drawing.createPicture(anchor, pictureIdx);

        if (resize) {
            picture.resize();
        }

        return picture;
    }

    /**
     * 创建下拉列表
     *
     * @param list
     */
    public void createListBox(String[] list) {
        // 文件初始化
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");

        // 在第一行第一个单元格，插入下拉框
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);

        // 普通写入操作
        cell.setCellValue("请选择");// 这是实验

        // 生成下拉列表

        // 只对（0，0）单元格有效
        CellRangeAddressList regions = new CellRangeAddressList(0, 0, 0, 0);

        // 生成下拉框内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(list);

        // 绑定下拉框和作用区域
        HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);

        // 对sheet页生效
        sheet.addValidationData(data_validation);

        // 写入文件
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("workbook.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 结束
        System.out.println("Over");
    }


    public static void write() throws IOException {
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 1000; rownum++) {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }
        // Rows with rownum < 900 are flushed and not accessible
        for (int rownum = 0; rownum < 900; rownum++) {
            Optional.ofNullable(sh.getRow(rownum)).orElseThrow(() -> new RuntimeException("row is null"));
        }
        // ther last 100 rows are still in memory
        for (int rownum = 900; rownum < 1000; rownum++) {
            Optional.ofNullable(sh.getRow(rownum)).orElseThrow(() -> new RuntimeException("row is null"));
        }
        FileOutputStream out = new FileOutputStream("D:\\download\\sxssf.xlsx");
        wb.write(out);
        out.close();
        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }


}

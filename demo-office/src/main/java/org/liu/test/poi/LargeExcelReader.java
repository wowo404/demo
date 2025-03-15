package org.liu.test.poi;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author lzs
 * @Date 2023/9/18 14:39
 **/
public class LargeExcelReader {
    public static void main(String[] args) throws Exception {
        InputStream inp = new FileInputStream("large_file.xlsx");
        XSSFReader reader = new XSSFReader(OPCPackage.open(inp));
        StylesTable styles = reader.getStylesTable();
        //reader.getSheet(rid)//此方法直接获取指定sheet，rid的格式为:rId + sheetIndex，sheetIndex从1开始
        //遍历所有的sheet
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) reader.getSheetsData();
        while (iter.hasNext()) {
            InputStream inputStream = iter.next();
            //开始处理数据，处理xml数据，参考：cn.hutool.poi.excel.sax.ExcelSaxUtil.readFrom
        }
        inp.close();
    }
}

package org.liu.hutool;

import cn.hutool.poi.excel.sax.handler.MapRowHandler;

import java.util.Map;

/**
 * @Author lzs
 * @Date 2022/12/3 11:15
 **/
public class MyRowHandler extends MapRowHandler {
    /**
     * 构造
     *
     * @param headerRowIndex 标题所在行（从0开始计数）
     * @param startRowIndex  读取起始行（包含，从0开始计数）
     * @param endRowIndex    读取结束行（包含，从0开始计数）
     */
    public MyRowHandler(int headerRowIndex, int startRowIndex, int endRowIndex) {
        super(headerRowIndex, startRowIndex, endRowIndex);
    }

    @Override
    public void handleData(int sheetIndex, long rowIndex, Map<String, Object> data) {
        System.out.println("row" + rowIndex + ":" + data.keySet() + ":" + data.values());
    }
}

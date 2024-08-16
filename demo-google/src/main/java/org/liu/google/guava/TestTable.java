package org.liu.google.guava;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 测试Table接口
 *
 * @Author lzs
 * @Date 2022/12/17 10:16
 **/
public class TestTable {

    public static void main(String[] args) {
        testArrayTable();
        testHashBasedTable();
        System.out.println(getFixedColumnNames());
    }

    private static void testHashBasedTable(){
        Table<String, String, String> table = HashBasedTable.create();
        table.put("a1", "b1", "c1");
        table.put("a2", "b2", "c2");
        table.put("a3", "b3", "c3");
        table.put("a3", "b3", "c4");

        System.out.println(table.containsRow("a1"));
        System.out.println(table.row("a1"));
        for (Table.Cell<String, String, String> cell : table.cellSet()) {
            System.out.println(cell);
        }
        System.out.println(table.size());
    }

    public static HashBasedTable<String, String, String> getFixedColumnNames() {
        HashBasedTable<String, String, String> table = HashBasedTable.create(4, 3);
        table.put("设区市营业收入", "setIndicatorValue", "指标值（亿元）");
        table.put("设区市营业收入", "setGrowthRate", "增速（%）");
        table.put("设区市营业收入", "setRanking", "排位");

        table.put("重点领域", "setCompanyNumbers", "企业数");
        table.put("重点领域", "setIndicatorValue", "指标值（亿元）");
        table.put("重点领域", "setGrowthRate", "增速（%）");

        table.put("分产业", "setCompanyNumbers", "企业数");
        table.put("分产业", "setIndicatorValue", "指标值（亿元）");
        table.put("分产业", "setGrowthRate", "增速（%）");

        table.put("县区", "setIndicatorValue", "指标值（亿元）");
        table.put("县区", "setGrowthRate", "增速（%）");
        table.put("县区", "setRanking", "排位");
        return table;
    }

    private static void testArrayTable(){
        List<Integer> rowKeys = new ArrayList<>(3);
        rowKeys.add(1);
        rowKeys.add(2);
        rowKeys.add(3);
        List<String> columnKeys = new ArrayList<>(3);
        columnKeys.add("a");
        columnKeys.add("b");
        columnKeys.add("c");
        ArrayTable<Integer, String, Object> arrayTable = ArrayTable.create(rowKeys, columnKeys);
        arrayTable.put(1, "a", "好");
        arrayTable.set(0, 1, "go");
//        arrayTable.put(1, "d", "haha");//不能插入没有事先定义的columnKey
        for (Table.Cell<Integer, String, Object> cell : arrayTable.cellSet()) {
            System.out.println(cell);
        }
    }

}

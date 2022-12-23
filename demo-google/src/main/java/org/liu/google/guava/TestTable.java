package org.liu.google.guava;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Set;

/**
 * 测试Table接口
 *
 * @Author lzs
 * @Date 2022/12/17 10:16
 **/
public class TestTable {

    public static void main(String[] args) {
        Table<String, String, String> table = HashBasedTable.create();
        table.put("a1", "b1", "c1");
        table.put("a2", "b2", "c2");
        table.put("a3", "b3", "c3");
        table.put("a3", "b3", "c3");

        System.out.println(table.containsRow("a1"));
        System.out.println(table.row("a1"));
    }

}

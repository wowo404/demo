package org.liu.google.guava;

import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 测试guava的新集合类型
 *
 * @author lzs
 * @Date 2024/8/16 11:48
 **/
public class TestNewCollectionTypes {

    public static void main(String[] args) {
        testMultiset();
    }

    public static void testMultiset() {
        Multiset<String> multiset = HashMultiset.create(5);
        multiset.add("a");
        multiset.add("b", 2);
        multiset.add("b");
        multiset.add("a");
        multiset.add("c");
        multiset.add("c");
        multiset.add("d");
        multiset.add("e");
        multiset.add("f");
        System.out.println(multiset);
        System.out.println(multiset.elementSet());
        System.out.println(multiset.count("b"));
        for (String one : multiset) {
            System.out.println(one);
        }
    }

    private static void testHashBasedTable() {
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

    private static void testArrayTable() {
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

    public void multimap() {
        Multimap<Integer, Info> multimap = ArrayListMultimap.create();

        Info info1 = new Info();
        info1.setId(1);
        multimap.put(1, info1);

        Info info2 = new Info();
        info2.setId(2);
        multimap.put(1, info2);

        Info info3 = new Info();
        info3.setId(3);
        multimap.put(2, info3);

        Set<Integer> keys = multimap.keySet();
        for (Integer key : keys) {
            List<Info> list = (List<Info>) multimap.asMap().get(key);
            System.out.println("key:" + key + ",list:" + list);
        }
        System.out.println("multimap keys:" + multimap.keys());
        Collection<Info> list = multimap.get(1);
        list.forEach(info -> System.out.println(info.getId()));
    }

    private class Info {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

}

package org.liu.collection;

import java.util.*;

/**
 * @author liuzhangsheng
 * @create 2019/5/9
 */
public class TestCollection {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, null, 2);
        for (Integer integer : list) {
            System.out.println(integer);
        }

        Set<Integer> set = new HashSet<>();
        set.add(null);
        set.add(1);
        set.forEach(System.out::println);

        //TreeSet,Hashtable,TreeMap都不接受null
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(null);
        treeSet.add(1);
        treeSet.forEach(System.out::println);

        Map<Integer, Integer> table = new Hashtable<>();
        table.put(1, null);
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " ---- " + entry.getValue());
        }

    }

}

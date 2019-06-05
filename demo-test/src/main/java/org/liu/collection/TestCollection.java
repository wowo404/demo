package org.liu.collection;

import org.liu.obj.Superior;

import java.util.*;

/**
 * @author liuzhangsheng
 * @create 2019/5/9
 */
public class TestCollection {

    public static void main(String[] args) {
        testRetry();
    }

    public static void testRetry(){
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ",");
                if (j == 3)
                    continue retry;//这里等同于break
            }
        }
        abc:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ",");
                if (j == 3)
                    break abc;//如果下面没有代码继续执行，则这里可以替换为return
            }
        }
        System.out.println("ok,it's done!");
        //不用标识位，通常的写法是如下：
        boolean tag = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ",");
                if (j == 3) tag = true;
            }
            if (tag) break;
        }
    }

    public static void test(){
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

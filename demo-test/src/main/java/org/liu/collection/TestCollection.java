package org.liu.collection;

import org.liu.model.Animal;
import org.liu.obj.Superior;

import java.util.*;

/**
 * @author liuzhangsheng
 * @create 2019/5/9
 */
public class TestCollection {

    public static void main(String[] args) {
        sort();
    }

    public static void sort(){
        Animal animal = new Animal();
        animal.setId(1);

        Animal animal2 = new Animal();
        animal2.setId(2);

        Animal animal3 = new Animal();
        animal3.setId(3);

        List<Animal> list = new ArrayList<>();
        list.add(animal2);
        list.add(animal);
        list.add(animal3);

        list.sort(Comparator.comparingInt(Animal::getId));

        for (Animal a : list) {
            System.out.println(a.getId());
        }
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
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(map.get(0));

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

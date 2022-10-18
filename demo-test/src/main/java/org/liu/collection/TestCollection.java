package org.liu.collection;

import cn.hutool.core.util.RandomUtil;
import org.liu.model.Animal;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuzhangsheng
 * @create 2019/5/9
 */
public class TestCollection {

    public static void main(String[] args) {
        removeAll();
    }

    public static void removeDynamic() {
        List<Animal> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Animal animal = new Animal();
            animal.setId(i);

            list.add(animal);
        }

        for (int i = 0; i < 11; i++) {
            if (list.size() == 0) continue;
            int random = RandomUtil.randomInt(list.size());
            System.out.println("size:" + list.size() + ",random:" + random);
            Animal randomAnimal = list.get(random);
            if (null != randomAnimal) {
                System.out.println(randomAnimal);
                list.remove(random);
            }
        }
        System.out.println(list);
    }

    public static void removeAll() {
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

        List<Integer> collect = list.stream().mapToInt(Animal::getId).boxed().collect(Collectors.toList());


        boolean b = collect.removeAll(Arrays.asList(1, 2));
        for (Integer integer : collect) {
            System.out.println(integer);
        }
    }

    public static void sort() {
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

    public static void treeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(4);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(1);
        treeSet.forEach(System.out::println);
        System.out.println(treeSet.last());
    }

    public static void test() {
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

    public static void treeMap() {
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("20200112110400-1-2", "abc2");
        treeMap.put("20200112110400-1-3", "abc3");
        treeMap.put("20200112110400-2-0", "abcd0");
        treeMap.put("20200112110400-2-1", "abcd1");
        treeMap.put("20200112110400-1-0", "abc0");
        treeMap.put("20200112110400-2-3", "abcd4");
        treeMap.put("20200112110400-1-1", "abc1");
        treeMap.put("20200112110400-2-2", "abcd2");

        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }
    }

    public static void subList() {
        List<Integer> list = new ArrayList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        List<Integer> subList = list.subList(0, 5);
        System.out.println(subList);
    }

}

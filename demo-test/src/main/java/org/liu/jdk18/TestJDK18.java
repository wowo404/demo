package org.liu.jdk18;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 参考详细介绍：
 * https://www.cnblogs.com/andywithu/p/7404101.html
 */
public class TestJDK18 {

    public void create(){

    }

    public void sort() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100"), 1),
                new Person("b", new BigDecimal("200"), 3), new Person("b", new BigDecimal("200"), 2));
        list.sort(Comparator.comparing(Person::getOrder));
        //另一种写法：(x,y) -> x.getOrder().compareTo(y.getOrder())
        //下面是idea的推荐
        //Reports Comparators defined as lambda expressions which could be expressed using methods like Comparator.comparing().
        //Some comparators like (person1, person2) -> person1.getName().compareTo(person2.getName()) could be simplified like this: Comparator.comparing(Person::getName).
        //Also suggests to replace chain comparisons with Comparator.thenComparing(), e.g. int res = o1.first.compareTo(o2.first); if(res == 0) res = o1.second.compareTo(o2.second); if(res == 0) res = o1.third - o2.third; return res; will be replaced with objs.sort(Comparator.comparing((Obj o) -> o.first).thenComparing(o -> o.second).thenComparingInt(o -> o.third)
        for (Person person : list) {
            System.out.println(person.getOrder());
        }
    }

    public void countSpecific() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100"), 1),
                new Person("b", new BigDecimal("200"), 2), new Person("b", new BigDecimal("200"), 3));
        List<Person> newList = list.parallelStream().filter(p -> p.getAge().compareTo(new BigDecimal("200")) == 0).collect(Collectors.toList());
        System.out.println(newList.size());
    }

    /**
     * 取list中某个字段的最小值
     */
    public void extractMin() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100"), 1),
                new Person("b", new BigDecimal("200"), 2));
        OptionalInt min = list.stream().mapToInt(p -> p.getOrder()).min();
        System.out.println(min.getAsInt());
    }

    /**
     * 取list中的bean的BigDecimal字段相加
     */
    public void collectBigDecimal() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100")),
                new Person("b", new BigDecimal("200")));
        BigDecimal sum = list.parallelStream().map(p -> p.getAge()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        System.out.println(sum);
        List<String> nameList = list.parallelStream().map(p -> p.getName()).collect(Collectors.toList());
        nameList.forEach(s -> {
            System.out.println(s);
        });
    }

    /**
     *  取list中的bean的某个字段转换成list
     */
    public void collectToList() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100")),
                new Person("b", new BigDecimal("200")));
        List<String> nameList = list.parallelStream().map(p -> p.getName()).collect(Collectors.toList());
        nameList.forEach(s -> {
            System.out.println(s);
        });
    }

    /**
     *  取list中的bean的某个字段去除重复并转换成list
     */
    public void collectToDistinctList() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100"), 1),
                new Person("b", new BigDecimal("200"), 1));
        List<Integer> nameList = list.parallelStream().map(p -> p.getOrder()).distinct().collect(Collectors.toList());
        nameList.forEach(s -> {
            System.out.println(s);
        });
    }

    /**
     * 判断list中某个字段是否匹配特定值
     */
    public void anyMatch() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100")),
                new Person("b", new BigDecimal("200")));
        boolean tag = list.parallelStream().anyMatch(p -> p.getName().equals("a"));
        System.out.println(tag);
    }

    /**
     * 约简操作
     */
    public void reduce() {
        Stream.of(1, 2, 3, 4, 5).reduce((a, b) -> a + b).ifPresent(System.out::println);

        Integer reduce = Stream.of(1, 2, 3, 4, 5).reduce(1, (a, b) -> a + b);
        System.out.println(reduce);

        //WARN:非并行操作时，第三个参数无效
        Integer result = Stream.of(1, 2, 3, 4, 5).reduce(2, (a, b) -> a + b, (a, b) -> a * b);
        System.out.println(result);

        //并行操作，1+2=3，2+2=4，3+2=5，4+2=6，5+2=7，最后合并是3*4*5*6*7=2520
        Integer result1 = Stream.of(1, 2, 3, 4, 5).parallel().reduce(2, (a, b) -> a + b, (a, b) -> a * b);
        System.out.println(result1);

        //并行操作list的例子
        Predicate<String> predicate = t -> t.contains("a");
        Stream.of("aa", "ab", "bc", "er", "ff").parallel().reduce(new ArrayList<String>(), (a, b) -> {
            if (predicate.test(b)) a.add(b);
            return a;
        }, (a, b) -> {
//            a.addAll(b);
            //TODO:这里遍历时有时值会是null，why
//            a.forEach(v -> System.out.println("from a:" + v));
//            b.forEach(v -> System.out.println("from b:" + v));
            return a;
        }).forEach(System.out::println);//TODO:这里遍历也会发生有时值会是null的情况，why

        Stream<String> s1 = Stream.of("aa", "ab", "c", "ad");

        System.out.println(s1.parallel().collect(() -> new ArrayList<String>(),
                (array, s) -> {if (predicate.test(s)) array.add(s); },
                (array1, array2) -> array1.addAll(array2)));
    }

    public void distinct(){
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100")),
                new Person("b", new BigDecimal("200")), new Person("a", new BigDecimal("300")));
        Set<String> set = new HashSet<>();
        list.stream().filter(person -> set.add(person.getName())).forEach(person -> System.out.println(person.getName() + person.getAge()));
    }

    public static void main(String[] args) {
        TestJDK18 test = new TestJDK18();
        test.distinct();
    }

    static class Person {
        private String name;
        private BigDecimal age;
        private Integer order;

        public Person(String name, BigDecimal age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, BigDecimal age, Integer order) {
            this.name = name;
            this.age = age;
            this.order = order;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getAge() {
            return age;
        }

        public void setAge(BigDecimal age) {
            this.age = age;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }
    }

}

package org.liu.jdk18;

import lombok.ToString;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collector;
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
        list.sort(Comparator.comparing(Person::getOrder, Comparator.reverseOrder()));
        //另一种写法：(x,y) -> x.getOrder().compareTo(y.getOrder())
        //下面是idea的推荐
        //Reports Comparators defined as lambda expressions which could be expressed using methods like Comparator.comparing().
        //Some comparators like (person1, person2) -> person1.getName().compareTo(person2.getName()) could be simplified like this: Comparator.comparing(Person::getName).
        //Also suggests to replace chain comparisons with Comparator.thenComparing(), e.g. int res = o1.first.compareTo(o2.first); if(res == 0) res = o1.second.compareTo(o2.second); if(res == 0) res = o1.third - o2.third; return res; will be replaced with objs.sort(Comparator.comparing((Obj o) -> o.first).thenComparing(o -> o.second).thenComparingInt(o -> o.third)
        for (Person person : list) {
            System.out.println(person.getOrder());
        }
        Optional<Person> max = list.stream().max((t1, t2) -> Math.min(t1.getOrder(), t2.getOrder()));
        max.ifPresent(System.out::println);
        Optional<Person> min = list.stream().max(Comparator.comparing(Person::getOrder));
        min.ifPresent(System.out::println);
    }

    public void countSpecific() {
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100"), 1),
                new Person("b", new BigDecimal("200"), 2), new Person("b", new BigDecimal("200"), 3));
        List<Person> newList = list.parallelStream().filter(p -> p.getAge().compareTo(new BigDecimal("200")) == 0).collect(Collectors.toList());
        System.out.println(newList.size());
        Optional<Person> first = list.stream().filter(person -> person.getOrder() == 2).findFirst();
        first.ifPresent(person -> System.out.println(person.getName() + person.getAge() + person.getOrder()));
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
        String collect = list.stream().map(person -> person.getName()).collect(Collectors.joining(","));
        System.out.println(collect);
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

    //过滤重复对象（通过指定字段过滤）
    public void distinct(){
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100")),
                new Person("b", new BigDecimal("200")), new Person("a", new BigDecimal("300")));
        Set<String> set = new HashSet<>();//通过Set的add方法返回true/false来去除重复
        list.stream().filter(person -> set.add(person.getName())).forEach(person -> System.out.println(person.getName() + person.getAge()));
    }

    //peek
    public void peek(){
        List<Person> list = Arrays.asList(new Person("a", new BigDecimal("100")),
                new Person("b", new BigDecimal("200")), new Person("a", new BigDecimal("300")));
        list.stream().filter(e -> e.getAge().compareTo(new BigDecimal("150")) > 0)
                .peek(e -> System.out.println(e.getName() + e.getAge()))
                .map(e -> e.getName().toUpperCase())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public void avg(){
        Stream.of(new BigDecimal("1.2"), new BigDecimal("3.7"))
                .mapToDouble(BigDecimal::doubleValue).average()
                .ifPresent(System.out::println);
    }

    public void toBytes(){
        List<Long> deletedIdList = new ArrayList<>();
        deletedIdList.add(1L);
        deletedIdList.add(2L);
        deletedIdList.add(3L);
        deletedIdList.add(4L);
        byte[][] bytes = deletedIdList.stream()
                .map(id -> ByteBuffer.allocate(8).putLong(id).array())
                .toArray(a -> new byte[deletedIdList.size()][]);
        System.out.println(bytes);
    }

    public void changeAndJoin(){
        String value = Stream.of("a", "b").map(val -> "'" + val + "'").collect(Collectors.joining(","));
        System.out.println(value);

        List<String> codes = new ArrayList<>();
        codes.add("a");
        codes.add("b");
        codes.add("c");
        String value2 = codes.stream().map(val -> "'" + val + "'").collect(Collectors.joining(","));
        System.out.println(value2);
    }

    public static void main(String[] args) {
        TestJDK18 test = new TestJDK18();
        test.changeAndJoin();
    }

    static final class MyCollectors {

        private MyCollectors() {}

        public static Collector<Byte, ?, byte[]> toByteArray() {
            return Collector.of(ByteArrayOutputStream::new, ByteArrayOutputStream::write, (baos1, baos2) -> {
                try {
                    baos2.writeTo(baos1);
                    return baos1;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }, ByteArrayOutputStream::toByteArray);
        }
    }

    @ToString
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

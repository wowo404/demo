package org.liu.jdk18;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.liu.model.SysDept;
import org.liu.model.TreeSelect;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaAndStream {

    List<Person> javaProgrammers = new ArrayList<Person>() {
        private static final long serialVersionUID = 1L;

        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43,
                    2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female",
                    23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33,
                    1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32,
                    1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22,
                    1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27,
                    1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30,
                    2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female",
                    35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33,
                    2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34,
                    1300));
        }
    };

    List<Person> phpProgrammers = new ArrayList<Person>() {
        private static final long serialVersionUID = 1L;

        {
            add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female",
                    23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32,
                    1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21,
                    1000));
            add(new Person("Osborne", "Shad", "PHP programmer", "male", 32,
                    1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25,
                    1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36,
                    1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21,
                    1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40,
                    1800));
        }
    };

    /**
     * 遍历，改变其中一列值后遍历
     */
    public void test1() {
        System.out.println("所有程序员的姓名和薪水:");
        javaProgrammers.forEach((p) -> System.out.printf(
                "%s %s; Salary: $%,d.", p.getFirstName(), p.getLastName(),
                p.getSalary()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; Salary: $%,d.",
                p.getFirstName(), p.getLastName(), p.getSalary()));

        System.out.println();
        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5
                + e.getSalary());

        //1.forEach方法中会调用Consumer的accept方法，使加薪操作生效
        //2.andThen方法是告诉Consumer调用完accept方法后执行执行的操作
        javaProgrammers.forEach(giveRaise.andThen((p) -> System.out.printf(
                "%s %s; Salary: $%,d.", p.getFirstName(), p.getLastName(),
                p.getSalary())));
        phpProgrammers.forEach(giveRaise.andThen((p) -> System.out.printf(
                "%s %s; Salary: $%,d.", p.getFirstName(), p.getLastName(),
                p.getSalary())));

        System.out.println();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String str = list.stream().map(val -> "'" + val + "'").collect(Collectors.joining(","));
        System.out.println(str);
    }

    /**
     * 过滤
     */
    public void test2() {
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers
                .stream()
                .filter((p) -> (p.getSalary() > 1400))
                .forEach(
                        (p) -> System.out.printf("%s %s; ", p.getFirstName(),
                                p.getLastName()));

        // 定义 filters
        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

        System.out.println();
        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers
                .stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach(
                        (p) -> System.out.printf("%s %s; ", p.getFirstName(),
                                p.getLastName()));

        // 重用filters
        System.out.println();
        System.out.println("年龄大于 24岁的女性 Java programmers:");
        javaProgrammers
                .stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach(
                        (p) -> System.out.printf("%s %s; ", p.getFirstName(),
                                p.getLastName()));
    }

    public void findFirst() {
        for (int i = 0; i < 10; i++) {
            Optional<Integer> first = Stream.of(1, 2, 3, 4).parallel().findFirst();
            System.out.println("findFirst.get() = " + first.get());
        }
    }

    public void findAny() {
        for (int i = 0; i < 10; i++) {
            Optional<Integer> first = Stream.of(1, 2, 3, 4).parallel().findAny();
            System.out.println("findAny.get() = " + first.get());
        }
    }

    /**
     * limit
     */
    public void test3() {
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));
        //使用limit方法,可以限制结果集的个数
        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers
                .stream()
                .limit(3)
                .forEach(
                        (p) -> System.out.printf("%s %s; ", p.getFirstName(),
                                p.getLastName()));

        System.out.println();
        System.out.println("最前面的3个女性 Java programmers:");
        javaProgrammers
                .stream()
                .filter(genderFilter)
                .limit(3)
                .forEach(
                        (p) -> System.out.printf("%s %s; ", p.getFirstName(),
                                p.getLastName()));
    }

    /**
     * 排序
     */
    public void test4() {
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo(p2
                        .getFirstName()))).limit(5)
                .collect(Collectors.toList());

        sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n",
                p.getFirstName(), p.getLastName()));

        System.out.println();
        System.out.println("根据 salary 排序 Java programmers:");
        sortedJavaProgrammers = javaProgrammers.stream()
                .sorted((p, p2) -> (p.getSalary() - p2.getSalary()))
                .collect(Collectors.toList());

        sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n",
                p.getFirstName(), p.getLastName()));
    }

    /**
     * min,max
     */
    public void test5() {
        System.out.println("工资最低的 Java programmer:");
        Person pers = javaProgrammers.stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary())).get();

        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(),
                pers.getLastName(), pers.getSalary());

        System.out.println();
        System.out.println("工资最高的 Java programmer:");
        Person person = javaProgrammers.stream()
                .max((p, p2) -> (p.getSalary() - p2.getSalary())).get();

        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(),
                person.getLastName(), person.getSalary());
    }

    /**
     * 结合 map 方法,我们可以使用 collect 方法来将我们的结果集放到一个字符串,一个 Set 或一个TreeSet中
     */
    public void test6() {
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers = phpProgrammers.stream()
                .map(Person::getFirstName).collect(Collectors.joining(" ; ")); // 在进一步的操作中可以作为标记(token)

        System.out.println(phpDevelopers);

        System.out.println();
        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaDevFirstName = javaProgrammers.stream()
                .map(Person::getFirstName).collect(Collectors.toSet());

        javaDevFirstName.forEach(p -> System.out.println(p));

        System.out.println();
        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers.stream()
                .map(Person::getLastName)
                .collect(Collectors.toCollection(TreeSet::new));

        javaDevLastName.forEach(p -> System.out.println(p));
    }

    /**
     * mapToInt
     */
    public void test7() {
        System.out.println("计算付给 Java programmers 的所有money:");
        int totalSalary = javaProgrammers.parallelStream()
                .mapToInt(p -> p.getSalary()).sum();
        System.out.println(totalSalary);
    }

    /**
     * 使用summaryStatistics方法获得stream 中元素的各种汇总数据
     */
    public void test8() {
        //计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());
    }

    /**
     * peek：中途消费一次，在操作引用对象且不需要返回值时更有用
     */
    public void test9() {
        SysDept dept0 = new SysDept();
        dept0.setDeptId(10L);
        dept0.setDeptName("aa10");
        List<SysDept> children = new ArrayList<>();
        children.add(dept0);
        children.stream().peek(dept -> dept.setEmail("test111")).forEach(System.out::println);

        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    /**
     * 测试flatMap类似于addAll的功能
     */
    public void test10() {
        List<Integer> collected0 = new ArrayList<>();
        collected0.add(1);
        collected0.add(3);
        collected0.add(5);
        List<Integer> collected1 = new ArrayList<>();
        collected1.add(2);
        collected1.add(4);
        collected1 = Stream.of(collected0, collected1)
                .flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collected1);// 1,3,5,2,4

        collected1 = Stream.of(collected0, collected1).flatMap(num -> {
            if (num.size() > 3)
                return num.stream();
            else
                return null;
        }).collect(Collectors.toList());
        System.out.println(collected1);
    }

    public void flatMap1() {
        Stream<Integer> s = Stream.of(new Integer[]{1, 2, 3}, new Integer[]{4, 5, 6}, new Integer[]{7, 8, 9}).flatMap(i -> Arrays.stream(i));
        s.forEach(System.out::println);
    }

    /**
     * reduce
     */
    public void test11() {
        int sumAll = Stream.of(1, 2, 3, 4).reduce(0,
                Integer::sum);// 给一个0是用来启动，的，若给-1，结果会是9
        System.out.println(sumAll);// 10
        Optional<Integer> optional = Stream.of(1, 2, 3, 4).reduce(Integer::sum);//  Java8提供了Integer的sum求和静态方法。
        System.out.println(optional.get());// 10
    }

    public void mapToIntTest() {
        IntSummaryStatistics intSummaryStatistics = javaProgrammers.stream().mapToInt(person -> person.getAge()).summaryStatistics();
        System.out.println("最大年龄：" + intSummaryStatistics.getMax()); //最大值
        System.out.println("最小年龄：" + intSummaryStatistics.getMin()); //最小值
        System.out.println("年龄总和：" + intSummaryStatistics.getSum()); //总计
        System.out.println("人数：" + intSummaryStatistics.getCount());   //个数
        System.out.println("平均年龄：" + intSummaryStatistics.getAverage());//平均数返回的是double类型
    }

    public void reduceTest() {
        Integer testInt[] = {};
        Optional<Integer> sumAll = Stream.of(testInt).reduce(Integer::sum);
        System.out.println(sumAll);// Optional.empty
        if (sumAll.isPresent()) {
            System.out.println(sumAll.get());
        } else {
            System.out.println(Optional.empty());// Optional.empty
        }

        sumAll.ifPresent(x -> {
            System.out.println(x); //sumAll不为空的时候，打印x的值；为空的时候，不做任何操作
        });

        System.out.println(sumAll.orElse(0));// 0

        System.out.println(sumAll.orElseGet(() -> 0)); // 0更省内存空间的方法

        Optional<List<Integer>> optional = Optional.of(new ArrayList<>());//of()里面不能传入null
        System.out.println(optional.get()); // []
        Optional<List<Integer>> optional1 = Optional.ofNullable(new ArrayList<>());
        System.out.println(optional1.get()); // []
        Optional<List<Integer>> emptyOptional = Optional.empty();
        Optional<List<Integer>> emptyOptional2 = Optional.ofNullable(null);
        System.out.println(emptyOptional.equals(emptyOptional2)); //true

    }

    public void reduceTest1() {
        Integer testInt[] = {1, 2, 3, 4};
        Optional<Integer> sumAll = Stream.of(testInt).reduce(Integer::sum);
        System.out.println(sumAll.filter(x -> x > 7));//Optional[10]
    }

    public void reduceTest2() {
        String testS[] = {"hello", " ", "world", " ", "!"};
        Optional<String> sumAll = Stream.of(testS).reduce(String::concat);
        System.out.println(sumAll.map(x -> null));//Optional.empty
        System.out.println(sumAll.map(x -> x.toUpperCase()));//Optional[HELLO WORLD !]
    }

    public void reduceTest3() {
        String testS[] = {"hello", " ", "world", " ", "!"};
        Optional<String> sumAll = Stream.of(testS).reduce(String::concat);
        System.out.println(sumAll.flatMap(x -> Optional.ofNullable(null)));//Optional.empty
        System.out.println(sumAll.flatMap(x -> Optional.of(x.toUpperCase())));//Optional[HELLO WORLD !]
    }

	/**
	 * 并行流
	 */
	public void parallel(){
		new Random().ints().limit(50).parallel().forEach(i->{
			System.out.println(Thread.currentThread().getName() + "--->" + i);
		});
	}

    /**
     * 生成集合
     */
    public void generateCollectionTest() {
        List<String> range = IntStream.range(0, 1000000009).boxed().map(i -> i + "").collect(Collectors.toList());
//		range.forEach(t -> System.out.println(t));
        range.size();
    }

    /**
	 * 调用构造方法
     * 把一个包含父子关系的集合拷贝到另一个同样包含父子关系的集合中，两个集合是不一样的对象
     */
    public void copyToNewObjectCollection() {
        SysDept dept0 = new SysDept();
        dept0.setDeptId(10L);
        dept0.setDeptName("aa10");
        List<SysDept> children = new ArrayList<>();
        children.add(dept0);

        SysDept dept = new SysDept();
        dept.setDeptId(1L);
        dept.setDeptName("a");
        dept.setChildren(children);

        SysDept dept1 = new SysDept();
        dept1.setDeptId(2L);
        dept1.setDeptName("b");
        dept1.setChildren(children);

        List<SysDept> list = new ArrayList<>();
        list.add(dept);
        list.add(dept1);

        List<TreeSelect> collect = list.stream().map(TreeSelect::new).collect(Collectors.toList());

        System.out.println(collect);

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class InnerDCSData {
        private String serialNum;
        private String id;
        private String value;
    }

    public void listToMap() {
        List<InnerDCSData> list = new ArrayList<>();
        list.add(new InnerDCSData("a-123", "1", "32.65"));
        list.add(new InnerDCSData("a-123", "2", "50.65"));
        list.add(new InnerDCSData("b-123", "3", "60.65"));
        //这个方法更加便捷
//		Map<String, List<InnerDCSData>> collect = list.stream().collect(Collectors.groupingBy(InnerDCSData::getId));
        Map<String, String> data = list.stream().collect(Collectors.toMap(InnerDCSData::getId, InnerDCSData::getValue));
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
    }

    public void maxValueInMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        Integer value = map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        System.out.println(value);
    }

    //取某一个字段放入数组
    public void toArray() {
        List<InnerDCSData> list = new ArrayList<>();
        list.add(new InnerDCSData("a-123", "1", "32.65"));
        list.add(new InnerDCSData("a-123", "2", "50.65"));
        list.add(new InnerDCSData("b-123", "3", "60.65"));
        String[] strings = list.stream().map(InnerDCSData::getId).collect(Collectors.toList()).toArray(new String[]{});
        System.out.println(Arrays.toString(strings));
        String[] array = list.stream().map(InnerDCSData::getSerialNum).toArray(num -> new String[list.size()]);
        System.out.println(Arrays.toString(array));
		String[] array1 = list.stream().map(InnerDCSData::getSerialNum).toArray(String[]::new);//最简洁的方式
		System.out.println(Arrays.toString(array1));
    }

    public void multiArray(){
        String[] arr1 = {"a", "b"};
        String[] arr2 = {"c", "d"};
        Stream.of(arr1, arr2).forEach(System.out::println);//每一个元素是数组
    }

    public static void main(String[] args) {
        LambdaAndStream ls = new LambdaAndStream();
//				ls.test1();
        //		ls.test2();
        //		ls.test3();
        //		ls.test4();
        //		ls.test5();
        //		ls.test6();
        //		ls.test7();
        //		ls.test8();
//		ls.test10();
//		ls.test11();
//		ls.flatMap1();
//		ls.maxValueInMap();
//		ls.copyToNewObjectCollection();
//		ls.toArray();
//        ls.findFirst();
//        ls.findAny();
//        ls.parallel();
        ls.multiArray();
    }

}

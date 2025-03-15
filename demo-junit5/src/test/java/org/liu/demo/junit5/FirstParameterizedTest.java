package org.liu.demo.junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.liu.demo.junit5.enums.StatusEnum;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author lzs
 * @Date 2024/8/16 17:08
 **/
public class FirstParameterizedTest {

    @ValueSource(ints = {1, 2, 3, 4})
    @ParameterizedTest
    void valueSource(int a) {
        System.out.println(a);
    }

    @NullSource
    @ParameterizedTest
    void nullSource(Integer a) {
        System.out.println(a);
    }

    //可以是string，list，set，map
    @EmptySource
    @ParameterizedTest
    void emptySource(Map<String, String> a) {
        System.out.println(a);
    }

    //使用正则匹配，mode必须是MATCH_ANY或MATCH_ALL
    @EnumSource(value = StatusEnum.class, names = {"^.*DISABLE$"}, mode = EnumSource.Mode.MATCH_ANY)
//    @EnumSource(value = StatusEnum.class, names = {"NORMAL", "TEMPORARY_DISABLE"})
    @ParameterizedTest
    void enumSource(StatusEnum statusEnum) {
        System.out.println(statusEnum);
    }

    @MethodSource("generateStrings")
    @ParameterizedTest
    void methodSource(String a) {
        System.out.println(a);
    }

    static Stream<String> generateStrings() {
        return Stream.of("a", "b", "c");
    }

    @CsvSource({
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1",
            "strawberry,    700_000"
    })
    @ParameterizedTest
    void csvSource(String fruit, int rank) {
        System.out.println(fruit + " -- " + rank);
    }

    @CsvFileSource(files = "E:\\work\\my\\code\\demo\\demo-junit5\\src\\test\\resources\\test.csv", useHeadersInDisplayName = true)
    //classpath模式匹配
//    @CsvFileSource(resources = "/test.csv", useHeadersInDisplayName = true)
    @ParameterizedTest(name = "[{index}] {arguments}")
    void csvFileSource(String title, Integer level, Integer age) {
        System.out.println(title + "-" + level + "-" + age);
    }

}

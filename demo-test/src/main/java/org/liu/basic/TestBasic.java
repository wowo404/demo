package org.liu.basic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author lzs
 * @Date 2023/1/11 8:59
 **/
public class TestBasic {

    public static void main(String[] args) {
        orAdd();
    }

    public static void orAdd() {
        List<List<Integer>> data = new ArrayList<>();

        List<Integer> list1 = Arrays.asList(1, 0, 0);
        List<Integer> list2 = Arrays.asList(0, 1, 0);
        List<Integer> list3 = Arrays.asList(0, 0, 0);
        data.add(list1);
        data.add(list2);
        data.add(list3);

        boolean flag = false;
        for (List<Integer> list : data) {
            flag |= list.contains(1);
        }
        System.out.println(flag);

        boolean flagTwo = true;
        for (List<Integer> list : data) {
            flagTwo &= list.contains(1);
        }
        System.out.println(flagTwo);
    }

    public static void bigDecimal() {
        BigDecimal bigDecimal = new BigDecimal("535.2553");
        System.out.println(bigDecimal.scale());
        System.out.println(bigDecimal.precision());
    }

}

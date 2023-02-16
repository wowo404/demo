package org.liu.basic;

import java.math.BigDecimal;

/**
 * @Author lzs
 * @Date 2023/1/11 8:59
 **/
public class TestBasic {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("535.2553");
        System.out.println(bigDecimal.scale());
        System.out.println(bigDecimal.precision());
    }

}

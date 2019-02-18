package org.liu.jdk18;

import java.util.function.Function;

/**
 * @author liuzhangsheng
 * @create 2019/1/21
 */
public class FunctionDemo {

    //API which accepts an implementation of
    //Function interface
    static void modifyTheValue(int valueToBeOperated, Function<Integer, Integer> function) {
        int newValue = function.apply(valueToBeOperated);
        /*
         * Do some operations using the new value.
         */
        System.out.println(newValue);
    }

    public static void main(String[] args) {
        int incr = 20;
        int myNumber = 10;

        modifyTheValue(myNumber, val -> {
            System.out.println(val);
            return val + incr;
        });

        myNumber = 15;
        modifyTheValue(myNumber, val -> val * 10);

        modifyTheValue(myNumber, val -> val - 100);

        modifyTheValue(myNumber, val -> "somestring".length() + val - 100);

    }
}

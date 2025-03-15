package org.liu.array;

import java.util.Arrays;

/**
 * @author liuzhangsheng
 * @create 2019/1/15
 */
public class ArrayUtil {

    public static void main(String[] args) {
        readTwo();
    }

    public static void readTwo() {
        int[] src = new int[]{1, 2, 3, 4, 5, 6};
        for (int i = 0, j = 1; i < src.length - 1; i += 2, j += 2) {
            System.out.println(src[i] + " -- " + src[j]);
        }
    }

    public void arrayCopy() {
        int[] src = new int[]{1, 2, 3, 4, 5, 6};
        int[] target = new int[3];
        System.arraycopy(src, 3, target, 0, 3);
        for (int i : src) {
            System.out.println(i);
        }
        System.out.println("--------");
        for (int i : target) {
            System.out.println(i);
        }
    }

    public void union() {
        int[] one = new int[]{1, 2, 3};
        int[] two = new int[]{4, 5, 6};
        int[] union = Arrays.copyOf(one, one.length + two.length);
        System.arraycopy(two, 0, union, one.length, two.length);
        for (int i : union) {
            System.out.println(i);
        }
    }

}

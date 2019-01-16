package org.liu.array;

/**
 * @author liuzhangsheng
 * @create 2019/1/15
 */
public class ArrayUtil {

    public static void main(String[] args) {
        int[] src = new int[]{1,2,3,4,5,6};
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

}

package algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author liuzhsh
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //这种方式不是冒泡排序，是选择排序
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] > arr[j]) {
//                    int temp = arr[j];
//                    arr[j] = arr[i];
//                    arr[i] = temp;
//                }
//            }
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + i + "轮排序结果：" + Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 9, 1, 0, 5, 4, 3, 8, 6, 7};
        bubbleSort(a);
        for (int i : a) {
            System.out.print(i + ",");
        }
    }

}

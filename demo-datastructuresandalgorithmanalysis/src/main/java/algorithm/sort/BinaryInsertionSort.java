package algorithm.sort;

import java.util.Arrays;

/**
 * 二分插入排序
 *
 * @Author lzs
 * @Date 2023/7/5 17:15
 **/
public class BinaryInsertionSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int left = 0;
            int right = i - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 4, 6, 9, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

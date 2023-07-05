package algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 桶排序
 * https://blog.csdn.net/Alian_1223/article/details/128016441
 */
public class BucketSort {
    public static void bucketSort(int[] array, int bucketSize) {
        if (array.length == 0) {
            return;
        }

        // 寻找数组中的最大值和最小值
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        int bucketCount = (int) Math.ceil((double) array.length / (double) bucketSize); // 计算桶的数量，使用向上取整的方法
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 将数组中的元素分配到各个桶中
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (array[i] - minValue) * (bucketCount - 1) / (maxValue - minValue);
            System.out.println(array[i] + "，放入" + bucketIndex + "号桶");
            buckets.get(bucketIndex).add(array[i]);
        }

        // 对每个桶中的元素进行排序，并将排序后的元素放回原数组
        int currentIndex = 0;
        for (int i = 0; i < bucketCount; i++) {
            ArrayList<Integer> bucket = buckets.get(i);
            Collections.sort(bucket);
            for (int j = 0; j < bucket.size(); j++) {
                array[currentIndex++] = bucket.get(j);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {45, 34, 65, 345, 35, 3, 666, 32, 1, 23, 87};
        bucketSort(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

}

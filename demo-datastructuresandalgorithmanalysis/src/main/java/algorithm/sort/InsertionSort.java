package algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 例如：5 3 7 4 6 9 0
 * 第一步：[5 3]拿第二个数3跟第一个数5比较，3比5小，则第二个数和第一个数交换位置。
 * 第二步：[5 3 7]拿第三个数跟第二个数比较，如果第三个数比第二个数小，则交换位置，再拿第二个数跟第一个数比较，如果第二个数比第一个数小，则交换位置，如果更大，则不交换。
 * 依次类推。
 *
 * @author liuzhsh
 */
public class InsertionSort {

    public static void insertionSort(int[] a) {
        int index;
        for (int i = 1; i < a.length; i++) {
            index = i;
            while (index > 0 && a[index - 1] > a[index]) {
                swap(a, index - 1, index);
                index--;
            }
            System.out.println("第" + i + "轮排序后：" + Arrays.toString(a));
        }
    }

    private static void swap(int[] a, int max, int min) {
        int temp = a[max];
        a[max] = a[min];
        a[min] = temp;
    }

    /**
     * 插入排序（精简版）
     * https://blog.csdn.net/Alian_1223/article/details/127392439
     * 此文章作者认为insertionSort方法是交换排序，没有体现插入的思想
     *
     * @param arr
     */
    public static void insertSortSimplify(int[] arr) {
        // 获取数组的长度
        int length = arr.length;
        System.out.println("总共要进行【" + (length - 1) + "】轮排序");
        // 从第二个数开始遍历
        for (int i = 1; i < length; i++) {
            System.out.println("-------------------------------------");
            // 当arr[i] 小于 arr[i - 1]才继续，因为取出数据arr[i]之前的数据经过插排一定是有序的，
            // 同理如果arr[i] 大于 arr[i - 1]插入位置就是当前位置
            if (arr[i] < arr[i - 1]) {
                // 获取当前待插入的数据
                int insertValue = arr[i];
                // 定义insertValue要插入的位置，默认是原位置:i
                int insertIndex = i;
                System.out.println("第【" + i + "】轮排序取出的数据为【" + insertValue + "】");
                while (insertIndex > 0 && insertValue < arr[insertIndex - 1]) {
                    // 比待插入数据大的数据，往后移动一个位置，也就是arr[insertIndex - 1]移动到arr[insertIndex],注意这个insertIndex是变化的
                    arr[insertIndex] = arr[insertIndex - 1];
                    System.out.println("待插入数据【" + insertValue + "】比【" + arr[insertIndex - 1] + "】小，【" + arr[insertIndex - 1] + "】往后移动，此时的数组变成--> " + Arrays.toString(arr));
                    insertIndex--;
                }
                // 把待插入的数据插入到索引为insertIndex的位置
                arr[insertIndex] = insertValue;
                System.out.println("第【" + i + "】轮排序最终【" + insertValue + "】插入到当前数组索引为【" + insertIndex + "】的位置");
            }
            System.out.println("第【" + i + "】轮排序后的数据：" + Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 7, 4, 6, 9, 0};
        insertionSort(a);
        System.out.println(Arrays.toString(a));

    }

}

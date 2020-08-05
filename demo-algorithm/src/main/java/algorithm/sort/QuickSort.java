package algorithm.sort;

/**
 * 快速排序
 * 该方法的基本思想是：
 * 1．先从数列中取出一个数作为基准数。
 * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 * 3．再对左右区间重复第二步，直到各区间只有一个数。
 *
 * @author liuzhsh
 */
public class QuickSort {

    private static void quickSort(int[] a, int l, int r) {
        if (l < r) {
            int i = l, j = r, x = a[l];
            while (i < j) {
                while (i < j && a[j] >= x) {// 从右向左查找第一个小于x的数
                    j--;
                }
                if (i < j) {
                    a[i] = a[j];//找到后将a[j]填充到a[i]，填充完后，a[j]位置已空
                }
                while (i < j && a[i] <= x) {// 从左向右找第一个大于等于x的数
                    i++;
                }
                if (i < j) {
                    a[j] = a[i];//填充a[j]位置
                }
            }
            a[i] = x;//将基准数填入a[i]中，此时i==j
            quickSort(a, l, i - 1);
            quickSort(a, i + 1, r);
        }
    }

    /**
     * for循环的快速排序[只需要这一个方法]
     *
     * @param array
     * @param start
     * @param end
     */
    private static void quickSort2(int[] array, int start, int end) {
        if (start < end) {
            int key = array[start];//初始化保存基元
            int i = start, j;//初始化i,j
            for (j = start + 1; j <= end; j++) {
                if (array[j] < key)//如果此处元素小于基元，则把此元素和i+1处元素交换，并将i加1，如大于或等于基元则继续循环
                {
                    int temp = array[j];
                    array[j] = array[i + 1];
                    array[i + 1] = temp;
                    i++;
                }
            }
            array[start] = array[i];//交换i处元素和基元
            array[i] = key;
            quickSort2(array, start, i - 1);//递归调用
            quickSort2(array, i + 1, end);
        }
    }

    public static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
			//从后往前比较
            while (end > start && a[end] >= key)
				//如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
			//从前往后比较
            while (end > start && a[start] <= key)
				//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
			//此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，
			// 但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) sort(a, end + 1, high);//右边序列。从关键值索引+1 到最后一个
    }

    public static void main(String[] args) {

//		int[] a = { 20, 3, 1, 6, 11, 2, 31, 32 };
        int[] a = {54, 35, 48, 36, 27, 12, 44, 44, 8, 14, 26, 17, 28};
		sort(a, 0, 12);
        for (int i : a) {
            System.out.println(i);
        }

    }

}

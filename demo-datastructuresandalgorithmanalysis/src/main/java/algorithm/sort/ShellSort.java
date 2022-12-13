package algorithm.sort;

/**
 * 希尔排序
 * 基本思想：先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。
 * 所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；然后，
 * 取第二个增量d2<d1重复上述的分组和排序，直至所取的增量dt=1(dt<dt-l<…<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。
 * @author liuzhsh
 */
public class ShellSort {

	private static void shellSort(int[] arr) {
		int j;
		int len = arr.length;
		for (int val = len >> 1; val > 0; val >>= 1) {
			//下面是对本次的所有分组做直接插入排序
			for (int i = val; i < len; i++) {
				int temp = arr[i];
				/*
				 * 为什么每次都用temp比较呢？
				 * 因为直接插入就是找到temp的合适位置。
				 * 为什么temp<arr[j-val]这个条件可以放在for内呢？
				 * 因为原来的组内数据已经有序，找到位置就停止便是。
				 * 不甚理解的去看直接插入排序吧。
				 */
				for (j = i; j >= val && temp < arr[j - val]; j -= val) {
					/*
					 * 为什么是arr[j-val]不是arr[j]呢？
					 * 因为j=i开始的，而且条件是j>=val&&temp<arr[j-val]
					 */
					arr[j] = arr[j - val];
				}
				/*
				 * 注意不是arr[i] = temp
				 * 直接插入排序也是这样的。
				 * 为什么呢？
				 * 因为j是位置，i是待插入元素
				 */
				arr[j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] a = {6,4,34,32,90,2};
		shellSort(a);
		for (int i : a) {
			System.out.println(i);
		}
	}

}

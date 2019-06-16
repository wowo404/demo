package algorithm.sort;

/**
 * 归并排序
 * 例如： 5 6 1 2 3 4 7 8
 * 第一步：
 * @author liuzhsh
 */
public class MergeSort {

	//将有二个有序数列a[first...mid]和a[mid...last]合并。
	void mergearray(int a[], int first, int mid, int last, int temp[]) {
		int i = first, j = mid + 1;
		int m = mid, n = last;
		int k = 0;

		while (i <= m && j <= n) {
			if (a[i] <= a[j])
				temp[k++] = a[i++];
			else
				temp[k++] = a[j++];
		}

		while (i <= m)
			temp[k++] = a[i++];

		while (j <= n)
			temp[k++] = a[j++];

		for (i = 0; i < k; i++)
			a[first + i] = temp[i];
	}

	void mergesort(int a[], int first, int last, int temp[]) {
		if (first < last) {
			int mid = (first + last) / 2;
			mergesort(a, first, mid, temp); //左边有序
			mergesort(a, mid + 1, last, temp); //右边有序
			mergearray(a, first, mid, last, temp); //再将二个有序数列合并
		}
	}

	void mergesort(int a[], int n) {
		int[] p = new int[n];
		mergesort(a, 0, n - 1, p);
	}

	//将有序数组a[]和b[]合并到c[]中
	void mergeOrderedArray(int a[], int n, int b[], int m, int c[]) {
		int i, j, k;

		i = j = k = 0;
		while (i < n && j < m) {
			if (a[i] < b[j])
				c[k++] = a[i++];
			else
				c[k++] = b[j++];
		}

		while (i < n)
			c[k++] = a[i++];

		while (j < m)
			c[k++] = b[j++];
	}

	public static void main(String[] args) {

		MergeSort sort = new MergeSort();
		int[] a = { 4, 3, 20, 22, 13, 15, 5, 8, 0, 2 };
		sort.mergesort(a, 10);
		for (int i : a) {
			System.out.println(i);
		}

		int[] ta = { 1, 3, 5 };
		int[] tb = { 2, 4, 6 };
		int[] tc = new int[6];
		sort.mergeOrderedArray(ta, 3, tb, 3, tc);
		for (int i : tc) {
			System.out.println(i);
		}
	}

}

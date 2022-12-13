package algorithm.sort;

/**
 * 插入排序
 * 例如：5 3 7 4 6 9 0
 * 第一步：[5 3]拿第二个数3跟第一个数5比较，3比5小，则第二个数和第一个数交换位置。
 * 第二步：[5 3 7]拿第三个数跟第二个数比较，如果第三个数比第二个数小，则交换位置，再拿第二个数跟第一个数比较，如果第二个数比第一个数小，则交换位置，如果更大，则不交换。
 * 依次类推。
 * @author liuzhsh
 */
public class InsertionSort {

	public static void insertionSort(int[] a){
		int index = 0;
		for (int i = 1; i < a.length; i++) {
			index = i;
			while(index > 0){
				if(a[index - 1] > a[index]){
					swap(a,index - 1,index);
					index--;
				} else {
					break;
				}
			}
		}
	}
	
	private static void swap(int[] a, int max, int min){
		int temp = a[max];
		a[max] = a[min];
		a[min] = temp;
	}
	
	public static void main(String[] args) {
		int[] a = {5,3,7,4,6,9,0};
		insertionSort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		
	}

}

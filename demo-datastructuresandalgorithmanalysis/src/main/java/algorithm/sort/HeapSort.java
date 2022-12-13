package algorithm.sort;

/**
 * 堆排序
 * @author liuzhsh
 */
public class HeapSort {

	public void heapSort(int[] a,int n){
		for(int i=n/2;i>=0;i--){
			heapAdjust(a,i,n);
		}
		for(int i = n - 1; i > 0; i--){
			swap(a, 0, i);
			heapAdjust(a, 0, i);
		}
	}

	private void heapAdjust(int[] a, int index, int length) {
		int childLeft;
		int tmp = a[index];
		for(; index*2 + 1 < length; index = childLeft){
			childLeft = index * 2 + 1;
			if(childLeft != length - 1 && a[childLeft] < a[childLeft + 1]){
				childLeft ++;
			}
			if(tmp > a[childLeft]){
				break;
			} else {
				a[index] = a[childLeft];
				index = childLeft;
			}
		}
		a[index] = tmp;
	}
	
	private void swap(int[] a, int m, int n) {
		int tmp = a[m];
		a[m] = a[n];
		a[n] = tmp;
	}

	public static void main(String[] args) {

		int[] a = {6,3,10,2,1,23,4};
		HeapSort sort = new HeapSort();
		sort.heapSort(a,7);
		for (int i : a) {
			System.out.println(i);
		}
	}

}

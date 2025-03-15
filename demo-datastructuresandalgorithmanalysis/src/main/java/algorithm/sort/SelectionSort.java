package algorithm.sort;

/**
 * 选择排序
 * @author liuzhsh
 */
public class SelectionSort {
	
	public static void selectionSort(int[] a){
		int min = 0;
		for(int i = 0; i < a.length; i++){
			min = i;
			for(int j = i + 1; j < a.length; j++){
				min = a[min] > a[j] ? j : min;
			}
			int temp = a[i];
			a[i] = a[min];
			a[min] = temp;
		}
	}

	public static void main(String[] args) {
		
		int[] a = {2,9,1,0,5,4,3,8,6,7};
        selectionSort(a);
		for (int i : a) {
			System.out.print(i + ",");
		}

	}

}

package algorithm.sort;

public class HeapSortMy {

	private void heapSort(int[] a,int n){
		for (int i = 0; 2*i+1 < n; i++) {
			if(a[2*i+1] < a[2*i+2]){
				if(a[i] > a[2*i+2]){
					swap(a,i,2*i+1);
				} else {
					
				}
			}
		}
	}
	
	private void swap(int[] a, int m, int n) {
		int tmp = a[m];
		a[m] = a[n];
		a[n] = tmp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

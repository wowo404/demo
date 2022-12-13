package algorithm.sort;

/**
 * 冒泡排序
 * @author liuzhsh
 */
public class BubbleSort {

	public static void bubbleSort(int[] A) {
        int temp;
        for(int i = 0;i < A.length; i++){
            for(int j = i + 1; j < A.length; j ++){
                if(A[i] > A[j]){
                    temp = A[j];
                    A[j] = A[i];
                    A[i] = temp;
                }
            }
        }
    }
	
	public static void main(String[] args) {
		int[] a = {2,9,1,0,5,4,3,8,6,7};
		bubbleSort(a);
		for (int i : a) {
			System.out.print(i + ",");
		}
	}

}

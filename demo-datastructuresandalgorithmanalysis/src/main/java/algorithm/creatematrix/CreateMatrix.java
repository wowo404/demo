package algorithm.creatematrix;

/**
 * 题目：编写程序形成一个n×n 的方阵a ，例当n=5时，得到：

          1  2  3  4  5
          2  1  3  4  5
          3  2  1  4  5
          4  3  2  1  5
          5  4  3  2  1

1 2 3 4 5 6  0-6
2 1 3 4 5 6  1-5
3 2 1 4 5 6  2-4
4 3 2 1 5 6  3-2
5 4 3 2 1 6  4-4
6 5 4 3 2 1  5-6

并且求出，当n=20时，反对角线元素的和：s=a(1,20)+...+a(20,1)为:
 * @author liuzhsh
 */
public class CreateMatrix {
	
	private static int[] generateArray(int size,int specIndex){
		int[] arr = new int[size];
		int right = size - specIndex;//1的右边有几个数,5-1=4
		int left = size - right;//1的左边有几个数,5-4=1
		
		arr[specIndex] = 1;//第specIndex位是1
		
		//填充数组右边
		for (int i = size - 1; i > specIndex; i--) {
			arr[i] = i + 1;
		}
		
		//填充数组左边
		for(int j = 0; j < left; j++){
			arr[j] = left - j + 1;
		}
		
		return arr;
	}

	public static void main(String[] args) {
		int n = 6;
		for (int i = 0; i < n; i++) {
			int[] arr = generateArray(n,i);
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[j] + " ");
			}
			System.out.println();
		}
		
	}

}

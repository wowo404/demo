package algorithm.maxsubsum;

import java.util.Random;

/**
 * 输入一组整数，求出这组数字子序列和中最大值。也就是只要求出最大子序列的和，不必求出最大的那个序列。例如：
 * 序列：-2 11 -4 13 -5 -2，则最大子序列和为20。
 * 序列：-6 2 4 -7 5 3 2 -1 6 -9 10 -2，则最大子序列和为16。
 * @author liuzhsh
 */
public class GetMaxSubSum {
	
	/**
	 * 查找方式：
	 * 从a[0]开始，比对a[0]+a[1]和thisSum，然后是a[0]+a[1]+...+a[n]，找到最大的子序列和赋给maxSum；
	 * 然后是a[1]，比对a[1]+a[2]和thisSum，然后是a[1]+a[2]+...+a[n]，
	 * 一直到a[n]。
	 */
	private static int maxSubSum1(int[] a){
		int maxSum = 0;
		for(int i = 0; i < a.length; i++){
			for(int j = i; j < a.length; j++){
				int thisSum = 0;
				for (int k = i; k <= j; k++) {
					thisSum += a[k];
				}
				if(thisSum > maxSum){
					maxSum = thisSum;
				}
			}
		}
		return maxSum;
	}
	
	/**
	 * 和maxSubSum1相比，减少了一个for循环
	 */
	private static int maxSubSum2(int[] a){
		int maxSum = 0;
		for(int i = 0; i < a.length; i++){
			int thisSum = 0;
			for(int j = i; j < a.length; j++){
				thisSum += a[j];
				if(thisSum > maxSum){
					maxSum = thisSum;
				}
			}
		}
		
		return maxSum;
	}
	
	/**
	 * 分治法（divide-and-conquer）
	 */
	private static int maxSubSum3(int[] a, int left, int right){
		if(left == right){
			if(a[left] > 0){
				return a[left];
			} else {
				return 0;
			}
		}
		
		int center = (left + right) / 2;
		int maxLeftSum = maxSubSum3(a, left, center);
		int maxRightSum = maxSubSum3(a,center + 1, right);
		
		//求出前半部分最大和
		int maxLeftBorderSum = 0,leftBorderSum = 0;
		for(int i = center; i >= left; i--){
			leftBorderSum += a[i];
			if(leftBorderSum > maxLeftBorderSum){
				maxLeftBorderSum = leftBorderSum;
			}
		}
		
		//求出后半部分最大和
		int maxRightBorderSum = 0,rightBorderSum = 0;
		for(int i = center + 1; i <= right; i++){
			rightBorderSum += a[i];
			if(rightBorderSum > maxRightBorderSum){
				maxRightBorderSum = rightBorderSum;
			}
		}
		
		return max3(maxLeftSum,maxRightSum,maxLeftBorderSum + maxRightBorderSum);
	}

	private static int max3(int a, int b, int c) {
		if(a > b){
			a = b;
		}
		if(a > c){
			return a;
		} else {
			return c;
		}
	}
	
	private static int maxSubSum4(int[] a){
		int maxSum = 0,thisSum = 0;
		for(int j = 0; j < a.length; j++){
			thisSum += a[j];
			if(thisSum > maxSum){
				maxSum = thisSum;
			} else if(thisSum < 0){
				thisSum = 0;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] a = generateArray(5);
		long before1 = System.currentTimeMillis();
		System.out.println(maxSubSum1(a));
		System.out.println("maxSubSum1 waste time:" + (System.currentTimeMillis() - before1));
		
		long before2 = System.currentTimeMillis();
		System.out.println(maxSubSum2(a));
		System.out.println("maxSubSum1 waste time:" + (System.currentTimeMillis() - before2));

		long before3 = System.currentTimeMillis();
		System.out.println(maxSubSum3(a,0,a.length - 1));
		System.out.println("maxSubSum1 waste time:" + (System.currentTimeMillis() - before3));
		
		long before4 = System.currentTimeMillis();
		System.out.println(maxSubSum4(a));
		System.out.println("maxSubSum1 waste time:" + (System.currentTimeMillis() - before4));
		
	}
	
	private static int[] generateArray(int length){
		Random r = new Random();
		int range = 10 * length;
		int[] temp = new int[length];
		for (int i = 0; i < length; i++) {
			temp[i] = r.nextInt(range);
		}
		return temp;
	}

}

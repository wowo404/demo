package org.liu.luckdraw;

import java.util.Random;

/**
 * 抽奖
 * @author liuzhsh 2017年6月15日
 */
public class LuckDraw {

	//奖池
	private static final Integer[] initPool = new Integer[100];
	
	static {
		for (int i = 0; i < 10; i++) {
			initPool[i] = 0;//0元的有10个数
		}
		for (int i = 10; i < 35; i++) {
			initPool[i] = 5;//5元的有25个数
		}
		for (int i = 35; i < 100; i++) {
			initPool[i] = 25;//25元的有65个数
		}
		//50，100元中奖概率为0，不放入奖池
	}
	
	public static void main(String[] args) {
		Integer[] pool = {10,25,65,0,0};
		for (int i = 0; i < 10; i++) {
			System.out.println(getRand(pool));
		}
		Integer countZero = 0;
		Integer countFive = 0;
		Integer countTwentyFive = 0;
		for (int i = 0; i < 100; i++) {
			Integer result = getRandTwo(initPool);
			if(result == 0){
				countZero ++;
			} else if(result == 5){
				countFive ++;
			} else if(result == 25){
				countTwentyFive ++;
			}
		}
		System.out.println("count result,zero=" + countZero + ",five=" + countFive + ",twentyFive=" + countTwentyFive);
		
	}
	
	/**
	 * 方法一
	 */
	private static Integer getRand(Integer[] pool){
		Integer result = null;
		int sum = 0;
		for (int i = 0; i < pool.length; i++) {
			sum += pool[i];
		}
		for (int i = 0; i < pool.length; i++) {
			int random = new Random().nextInt(sum);
			if(random < pool[i]){
				result = i;
				break;
			} else {
				sum -=pool[i];
			}
		}
		return result;
	}
	
	/**
	 * 方法二
	 */
	private static Integer getRandTwo(Integer[] pool){
		int random = new Random().nextInt(100);
		return pool[random];
	}
	
}

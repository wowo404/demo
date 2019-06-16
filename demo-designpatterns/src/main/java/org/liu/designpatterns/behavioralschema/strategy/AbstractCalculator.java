package org.liu.designpatterns.behavioralschema.strategy;

/**
 * 策略模式
 * 设计一个抽象类（可有可无，属于辅助类），提供辅助函数
 * @author liuzhsh
 */
public abstract class AbstractCalculator {
	
	public int[] split(String exp,String opt){
		String[] array = exp.split(opt);
		int[] arrayInt = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}

}

package org.liu.designpatterns.behavioralschema.templetemethod;

/**
 * 模板方法模式
 * @author liuzhsh
 */
public abstract class AbstractCalculator {
	
	public final int calculate(String exp, String opt){
		int[] array = split(exp,opt);
		return calculate(array[0],array[1]);
	}
	
	abstract int calculate(int i, int j);

	public int[] split(String exp,String opt){
		String[] array = exp.split(opt);
		int[] arrayInt = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}

}

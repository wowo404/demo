package org.liu.designpatterns.behavioralschema.strategy;

/**
 * 策略模式
 * @author liuzhsh
 */
public class Plus extends AbstractCalculator implements ICalculator {

	@Override
	public int calculate(String exp) {
		int[] array = split(exp,"\\+");
		return array[0] + array[1];
	}

}

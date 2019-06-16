package org.liu.designpatterns.behavioralschema.interpreter;

/**
 * 解释器模式（Interpreter）
 * @author liuzhsh
 */
public class Plus implements Expression {

	@Override
	public int interpret(Context context) {
		return context.getNum1()+context.getNum2();
	}

}

package org.liu.designpatterns.behavioralschema.interpreter;

/**
 * 解释器模式（Interpreter）
 * @author liuzhsh
 */
public interface Expression {
	
	public int interpret(Context context);

}

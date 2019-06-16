package org.liu.designpatterns.createschema.singleton;

/**
 * 饿汉式
 * @author liuzhsh
 */
public class ClassA {
	
	private static ClassA a = new ClassA();
	
	public static ClassA newInstance(){
		return a;
	}
	
	private ClassA(){}

}

package org.liu.designpatterns.createschema.singleton;

/**
 * 懒汉式
 * @author liuzhsh
 */
public class ClassB {
	
	private static ClassB b = null;
	
	public static ClassB newInstance(){
		if(null == b){
			syncInit();
		}
		return b;
	}
	
	/**
	 * 20160323优化，只在创建实例的时候添加同步锁
	 * 以前的做法是在newInstance方法上加synchronized，这个做法在每次获取实例时都有同步锁，对性能有所影响
	 */
	private static synchronized void syncInit(){
		if(null == b){
			b = new ClassB();
		}
	}

	private ClassB(){}
	
}

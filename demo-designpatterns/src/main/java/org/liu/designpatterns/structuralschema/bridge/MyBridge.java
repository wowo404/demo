package org.liu.designpatterns.structuralschema.bridge;

/**
 * 桥接模式
 * @author liuzhsh
 */
public class MyBridge extends Bridge {
	
	@Override
	public void method(){
		getSource().method();
	}

}

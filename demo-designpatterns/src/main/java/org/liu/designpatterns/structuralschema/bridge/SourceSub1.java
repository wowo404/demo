package org.liu.designpatterns.structuralschema.bridge;

/**
 * 桥接模式
 * @author liuzhsh
 */
public class SourceSub1 implements Sourceable {

	@Override
	public void method() {
		System.out.println("this is the first sub!");
	}

}

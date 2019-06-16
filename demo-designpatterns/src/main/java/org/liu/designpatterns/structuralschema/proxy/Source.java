package org.liu.designpatterns.structuralschema.proxy;

/**
 * 代理模式
 * @author liuzhsh
 */
public class Source implements Sourceable {

	@Override
	public void method() {
		System.out.println("the original method!");
	}

}

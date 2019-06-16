package org.liu.designpatterns.structuralschema.decorator;

/**
 * 被装饰类
 * @author liuzhsh
 */
public class Source implements Sourceable {

	@Override
	public void method() {
		System.out.println("the original method!");
	}

}

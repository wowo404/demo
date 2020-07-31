package org.liu.designpatterns.structuralschema.decorator;

/**
 * 装饰类
 * @author liuzhsh
 */
public class Decorator implements Sourceable{

	private Sourceable source;//关键点，持有一个被装饰对象的抽象类或接口
	
	public Decorator(Sourceable source){
		super();
		this.source = source;
	}
	
	@Override
	public void method() {
		System.out.println("before decorator!");
		source.method();
		System.out.println("after decorator!");
	}

}

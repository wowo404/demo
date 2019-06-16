package org.liu.designpatterns.structuralschema.adapter.objectadapter;

/**
 * 适配器模式，实现Targetable
 * @author liuzhsh
 */
public class Wrapper implements Targetable {

	//不继承source，持有一个Source类的实例，以达到解决兼容性的问题
	private Source source;
	
	public Wrapper(Source source){
		this.source = source;
	}
	
	@Override
	public void method1() {
		source.method1();
	}
	
	@Override
	public void method2() {
		System.out.println("this is targetable method!");
	}

}

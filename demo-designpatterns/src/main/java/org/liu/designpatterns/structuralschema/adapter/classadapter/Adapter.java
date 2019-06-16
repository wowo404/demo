package org.liu.designpatterns.structuralschema.adapter.classadapter;

/**
 * 适配器模式，继承Source，实现Targetable
 * @author liuzhsh
 */
public class Adapter extends Source implements Targetable {

	@Override
	public void method2() {
		System.out.println("this is targetable method!");
	}

}

package org.liu.designpatterns.structuralschema.adapter.interfaceadapter;

/**
 * 实现了抽象累Wrapper的method1
 * @author liuzhsh
 */
public class SourceSub1 extends Wrapper {

	@Override
	public void method1() {
		System.out.println("the sourceable interface's first sub1!");
	}

}

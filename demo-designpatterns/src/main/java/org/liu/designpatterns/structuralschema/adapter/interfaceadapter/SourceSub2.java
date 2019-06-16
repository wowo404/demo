package org.liu.designpatterns.structuralschema.adapter.interfaceadapter;

/**
 * 实现了抽象累Wrapper的method2
 * @author liuzhsh
 */
public class SourceSub2 extends Wrapper {

	@Override
	public void method2() {
		System.out.println("the sourceable interface's second sub2!");
	}

}

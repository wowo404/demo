package org.liu.designpatterns.structuralschema.adapter.interfaceadapter;

/**
 * 接口的适配器模式
 * 接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。
 * @author liuzhsh
 */
public class WrapperTest {

	public static void main(String[] args) {
		Sourceable s1 = new SourceSub1();
		Sourceable s2 = new SourceSub2();
		
		s1.method1();
		s1.method2();
		System.out.println("--------------");
		s2.method1();
		s2.method2();
	}

}

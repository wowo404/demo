package org.liu.designpatterns.structuralschema.adapter.objectadapter;

/**
 * 对象的适配器模式test
 * 和类的适配器模式相比，只是适配方式不同
 * 对象的适配器模式：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
 * @author liuzhsh
 */
public class AdapterTest {

	public static void main(String[] args) {
		//这样Targetable接口的实现类就具有了Source类的功能。
		Targetable target = new Wrapper(new Source());
		target.method1();
		target.method2();
	}

}

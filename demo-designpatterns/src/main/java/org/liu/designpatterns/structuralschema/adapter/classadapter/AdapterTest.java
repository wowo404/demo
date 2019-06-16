package org.liu.designpatterns.structuralschema.adapter.classadapter;

/**
 * 类的适配器模式test
 * 类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
 * @author liuzhsh
 */
public class AdapterTest {

	public static void main(String[] args) {
		//这样Targetable接口的实现类就具有了Source类的功能。
		Targetable target = new Adapter();
		target.method1();
		target.method2();
	}

}

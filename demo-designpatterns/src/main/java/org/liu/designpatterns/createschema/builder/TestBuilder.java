package org.liu.designpatterns.createschema.builder;

/**
 * 建造模式（Builder） 将一个对象的内部表象和建造过程分割，一个建造过程可以造出不同表象的对象。可简化为模版方法模式。
 * @author liuzhsh
 */
public class TestBuilder {

	public static void main(String[] args) {

		Builder b = new BuilderImpl();
		
		Director d = new Director(b);//这里可以传入各种不同的Builder实现类
		Product p = d.createProduct();
		System.out.println(p);
		
	}

}

package org.liu.designpatterns.createschema.singleton;

/**
 * 单例模式（Singleton） 改善全局变量和命名空间的冲突，可以说是一种改良了的全局变量。
 * 这种一个类只有一个实例，且提供一个访问全局点的方式，更加灵活的保证了实例的创建和访问约束。
 * 系统中只有一个实例，因此构造方法应该为私有 饿汉式：类加载时直接创建静态实例；
 * 懒汉式：第一次需要时才创建一个实例，那么newInstance方法要加同步。
 * 饿汉式比懒汉式要好，尽管资源利用率要差。但是不用同步。
 * @author liuzhsh
 */
public class TestSingleton {

	public static void main(String[] args) {

		//饿汉式
		ClassA a = ClassA.newInstance();
		System.out.println(a);
		//懒汉式
		ClassB b = ClassB.newInstance();
		System.out.println(b);
		
	}

}

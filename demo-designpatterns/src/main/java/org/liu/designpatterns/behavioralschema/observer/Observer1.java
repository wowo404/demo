package org.liu.designpatterns.behavioralschema.observer;

/**
 * 观察者模式（Observer）
 * @author liuzhsh
 */
public class Observer1 implements Observer {

	@Override
	public void update() {
		System.out.println("observer1 has received!");
	}

}

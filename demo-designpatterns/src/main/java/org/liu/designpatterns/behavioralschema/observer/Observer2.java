package org.liu.designpatterns.behavioralschema.observer;

/**
 * 观察者模式（Observer）
 * @author liuzhsh
 */
public class Observer2 implements Observer {

	@Override
	public void update() {
		System.out.println("observer2 has received!");
	}

}

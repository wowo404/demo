package org.liu.designpatterns.behavioralschema.observer;

/**
 * 观察者模式（Observer）
 * @author liuzhsh
 */
public class MySubject extends AbstractSubject {

	@Override
	public void operations() {
		System.out.println("update self!");
		notifyObservers();
	}

}

package org.liu.designpatterns.behavioralschema.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 观察者模式（Observer）
 * @author liuzhsh
 */
public abstract class AbstractSubject implements Subject {
	
	private Vector<Observer> vector = new Vector<Observer>();

	@Override
	public void add(Observer observer) {
		vector.add(observer);
	}

	@Override
	public void del(Observer observer) {
		vector.remove(observer);
	}

	@Override
	public void notifyObservers() {
		Enumeration<Observer> enume = vector.elements();
		while(enume.hasMoreElements()){
			enume.nextElement().update();
		}
	}
	
}

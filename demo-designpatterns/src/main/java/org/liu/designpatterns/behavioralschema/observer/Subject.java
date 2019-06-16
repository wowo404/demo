package org.liu.designpatterns.behavioralschema.observer;

/**
 * 观察者模式（Observer）
 * @author liuzhsh
 */
public interface Subject {
	
	/**
	 * 增加观察者
	 */
	public void add(Observer observer);
	
	/**
	 * 删除观察者
	 */
	public void del(Observer observer);
	
	/**
	 * 通知所有的观察者
	 */
	public void notifyObservers();
	
	/**
	 * 自身的操作
	 */
	public void operations();

}

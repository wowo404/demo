package org.liu.designpatterns.behavioralschema.iterator;

/**
 * 迭代器模式（Iterator）
 * @author liuzhsh
 */
public interface Iterator {
	
	public Object previous();
	
	public Object next();
	
	public boolean hasNext();
	
	public Object first();

}

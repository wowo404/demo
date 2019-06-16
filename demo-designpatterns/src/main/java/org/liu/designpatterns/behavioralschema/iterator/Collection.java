package org.liu.designpatterns.behavioralschema.iterator;

/**
 * 迭代器模式（Iterator）
 * @author liuzhsh
 */
public interface Collection {
	
	public Iterator iterator();
	
	public Object get(int i);
	
	public int size();

}

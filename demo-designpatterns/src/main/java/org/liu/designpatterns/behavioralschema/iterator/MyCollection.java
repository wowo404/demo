package org.liu.designpatterns.behavioralschema.iterator;

/**
 * 迭代器模式（Iterator）
 * @author liuzhsh
 */
public class MyCollection implements Collection{

	private Object[] obj;
	
	public MyCollection(Object[] obj){
		this.obj = obj;
	}
	
	@Override
	public Iterator iterator() {
		return new MyIterator(this);
	}

	@Override
	public Object get(int i) {
		return obj[i];
	}

	@Override
	public int size() {
		return obj.length;
	}

}

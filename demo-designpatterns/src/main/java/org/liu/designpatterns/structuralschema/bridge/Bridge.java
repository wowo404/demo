package org.liu.designpatterns.structuralschema.bridge;

/**
 * 桥接模式
 * @author liuzhsh
 */
public abstract class Bridge {
	
	private Sourceable source;

	public Sourceable getSource() {
		return source;
	}

	public void setSource(Sourceable source) {
		this.source = source;
	}
	
	public void method(){
	}

}

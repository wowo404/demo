package org.liu.designpatterns.createschema.builder;

public interface Builder {
	
	void buildHead();
	
	void buildCenter();
	
	void buildTail();
	
	Product getProduct();

}

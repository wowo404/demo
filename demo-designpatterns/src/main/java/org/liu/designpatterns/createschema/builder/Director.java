package org.liu.designpatterns.createschema.builder;

public class Director {
	
	Builder b;
	
	public Director(Builder b){
		this.b = b;
	}
	
	public Product createProduct(){
		b.buildHead();
		b.buildCenter();
		b.buildTail();
		return b.getProduct();
	}

}

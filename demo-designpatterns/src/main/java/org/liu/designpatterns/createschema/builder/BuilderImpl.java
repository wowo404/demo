package org.liu.designpatterns.createschema.builder;

public class BuilderImpl implements Builder {

	@Override
	public void buildHead() {
		System.out.println("The head is build.");
	}

	@Override
	public void buildCenter() {
		System.out.println("The center is build.");
	}

	@Override
	public void buildTail() {
		System.out.println("The tail is build.");
	}

	@Override
	public Product getProduct() {
		return new Product();
	}

}

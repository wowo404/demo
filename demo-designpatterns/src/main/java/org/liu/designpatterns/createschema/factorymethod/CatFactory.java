package org.liu.designpatterns.createschema.factorymethod;

public class CatFactory extends AnimalFactory {

	@Override
	Animal getAnimal() {
		System.out.println("A cat!");
		return new Cat();
	}

}

package org.liu.designpatterns.createschema.factorymethod;

public class CatFactory extends AnimalFacoty {

	@Override
	Animal getAnimal() {
		System.out.println("A cat!");
		return new Cat();
	}

}

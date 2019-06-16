package org.liu.designpatterns.createschema.factorymethod;

public class DogFactory extends AnimalFacoty{

	@Override
	Animal getAnimal() {
		System.out.println("A Dog!");
		return new Dog();
	}

}

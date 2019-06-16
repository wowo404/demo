package org.liu.designpatterns.createschema.abstractfactory;

import java.awt.Component;
import java.awt.Frame;

public abstract class GUIFactory {
	
	abstract Component getButton();
	
	abstract Component getTextField();
	
	abstract Frame getFrame();

}

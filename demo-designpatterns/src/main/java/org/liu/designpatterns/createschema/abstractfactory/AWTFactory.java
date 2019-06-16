package org.liu.designpatterns.createschema.abstractfactory;

import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.TextField;

public class AWTFactory extends GUIFactory {

	@Override
	Component getButton() {
		return new Button("AWT Button");
	}

	@Override
	Component getTextField() {
		return new TextField(20);
	}

	@Override
	Frame getFrame() {
		return new Frame("AWT Frame");
	}

}

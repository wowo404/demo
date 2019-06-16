package org.liu.designpatterns.createschema.abstractfactory;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SwingFactory extends GUIFactory {

	@Override
	Component getButton() {
		return new JButton("Swing Button");
	}

	@Override
	Component getTextField() {
		return new JTextField(20);
	}

	@Override
	Frame getFrame() {
		return new JFrame("Swing Frame");
	}

}

package org.liu.designpatterns.createschema.abstractfactory;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 抽象工厂模式（Abstract Factory） 针对多个产品等级的情况，而工厂方法模式针对单一产品等级的情况。
 * @author liuzhsh
 */
public class TestAbstractFactory {

	public static void main(String[] args) {

		GUIFactory gui = new SwingFactory();
		Frame frame = gui.getFrame();
		Component button = gui.getButton();
		Component textField = gui.getTextField();
		
		frame.setSize(500, 300);
		frame.setLayout(new FlowLayout());
		frame.add(button);
		frame.add(textField);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		
	}

}

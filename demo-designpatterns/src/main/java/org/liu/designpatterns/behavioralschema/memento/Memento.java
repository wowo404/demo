package org.liu.designpatterns.behavioralschema.memento;

/**
 * 备忘录模式（Memento）
 * @author liuzhsh
 */
public class Memento {

	private String value;

	public Memento(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

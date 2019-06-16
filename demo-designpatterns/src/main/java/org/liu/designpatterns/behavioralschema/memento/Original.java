package org.liu.designpatterns.behavioralschema.memento;

/**
 * 备忘录模式（Memento）
 * @author liuzhsh
 */
public class Original {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Original(String value) {
		this.value = value;
	}

	public Memento createMemento() {
		return new Memento(value);
	}

	public void restoreMemento(Memento memento) {
		this.value = memento.getValue();
	}

}

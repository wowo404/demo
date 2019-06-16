package org.liu.designpatterns.behavioralschema.memento;

/**
 * 备忘录模式（Memento）
 * @author liuzhsh
 */
public class Storage {

	private Memento memento;

	public Storage(Memento memento) {
		this.memento = memento;
	}

	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}

}

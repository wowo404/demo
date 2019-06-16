package org.liu.designpatterns.behavioralschema.mediator;

/**
 * 中介者模式（Mediator）
 * @author liuzhsh
 */
public abstract class User {

	private Mediator mediator;

	public Mediator getMediator() {
		return mediator;
	}

	public User(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void work();

}

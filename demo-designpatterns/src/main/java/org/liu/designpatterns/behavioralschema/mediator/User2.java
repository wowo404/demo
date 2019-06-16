package org.liu.designpatterns.behavioralschema.mediator;

/**
 * 中介者模式（Mediator）
 * @author liuzhsh
 */
public class User2 extends User {

	public User2(Mediator mediator) {
		super(mediator);
	}

	@Override
	public void work() {
		System.out.println("user2 exe!");
	}

}

package org.liu.designpatterns.behavioralschema.mediator;

/**
 * 中介者模式（Mediator）
 * @author liuzhsh
 */
public class User1 extends User {

	public User1(Mediator mediator) {
		super(mediator);
	}

	@Override
	public void work() {
		System.out.println("user1 exe!");
	}

}

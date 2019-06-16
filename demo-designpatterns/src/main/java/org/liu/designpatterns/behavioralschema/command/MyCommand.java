package org.liu.designpatterns.behavioralschema.command;

/**
 * 命令模式（Command）
 * @author liuzhsh
 */
public class MyCommand implements Command {

	private Receiver receiver;
	
	public MyCommand(Receiver receiver){
		this.receiver = receiver;
	}
	
	@Override
	public void exec() {
		receiver.action();
	}

}

package org.liu.designpatterns.behavioralschema.command;

/**
 * 命令模式（Command）
 * @author liuzhsh
 */
public class Invoker {
	
	private Command command;
	
	public Invoker(Command command){
		this.command = command;
	}
	
	public void action(){
		command.exec();
	}

}

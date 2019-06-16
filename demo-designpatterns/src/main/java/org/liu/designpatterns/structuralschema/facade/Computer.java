package org.liu.designpatterns.structuralschema.facade;

/**
 * 外观模式
 * @author liuzhsh
 */
public class Computer {
	
	private CPU cpu;
	private Memory memory;
	private Disk disk;
	
	public Computer(){
		cpu = new CPU();
		memory = new Memory();
		disk = new Disk();
	}
	
	public void startup(){
		System.out.println("start the computer!");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("start the computer finished!");
	}

	public void shutdown(){
		System.out.println("shutdown the computer!");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("shutdown the computer finished!");
	}
	
}

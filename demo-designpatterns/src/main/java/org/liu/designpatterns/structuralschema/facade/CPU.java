package org.liu.designpatterns.structuralschema.facade;

/**
 * 外观模式
 * @author liuzhsh
 */
public class CPU {
	
	public void startup(){
		System.out.println("CPU startup!");
	}
	
	public void shutdown(){
		System.out.println("CPU shutdown!");
	}

}

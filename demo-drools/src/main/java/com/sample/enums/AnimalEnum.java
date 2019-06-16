/**
 * copyright liu
 */
package com.sample.enums;

/**
 * @author liuzhsh
 *
 */
public enum AnimalEnum {
	
	LION(0,"ʨ��"),TIGER(1,"�ϻ�"),WOLF(2,"��"),DOG(3,"��");
	
	public int index;
	public String name;
	
	private AnimalEnum(int index,String name){
		this.index = index;
		this.name = name;
	}
	
}

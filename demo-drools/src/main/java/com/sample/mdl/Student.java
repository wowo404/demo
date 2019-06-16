package com.sample.mdl;

import lombok.Data;

@Data
public class Student {
	
	private String name;
	private String id;
	private String[] nickname;
	private int age;
	private int index;
	private School school;
	private int beCounted;//0-δ��ͳ�ƣ�1-�ѱ�ͳ��
	
}

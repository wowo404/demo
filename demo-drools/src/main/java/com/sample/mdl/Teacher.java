package com.sample.mdl;

import lombok.Data;

@Data
public class Teacher {
	
	private String name;
	private String id;
	private String[] nickname;
	private int age;
	private int index;
	private School school;

}

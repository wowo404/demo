package com.sample.mdl;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class School {
	
	private String name;
	private int years;
	private String address;
	private String slogon;
	private String[] nickname;
	private List<String> gradeList;
	private Map<String,String> advertisementMap;
	private int studentNumber;
	
	public static void say(String studentName){
		System.out.println("Hello, " + studentName + ".Welcome to this school!");
	}

}

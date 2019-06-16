package org.liu.classloader;

public class TestClassLoader {

	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		System.out.println(TestClassLoader.class.getClassLoader());
		System.out.println(Integer.class.getClassLoader());
	}

}

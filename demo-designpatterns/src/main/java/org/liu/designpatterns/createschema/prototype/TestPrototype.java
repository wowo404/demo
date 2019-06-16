package org.liu.designpatterns.createschema.prototype;

public class TestPrototype {

	public static void main(String[] args) {

		Father f = new Father();
		
		User u1 = new User("123456",f);
		try {
			User u2 = u1.clone();
			System.out.println(u1 == u2);//false
			System.out.println(u1.f == u2.f);//true
			
			User u3 = (User) u1.deepClone();
			System.out.println(u1 == u3);//false
			System.out.println(u1.f == u3.f);//false
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}

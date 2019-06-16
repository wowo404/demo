package org.liu.designpatterns.createschema.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Cloneable,Serializable{

	private static final long serialVersionUID = 1L;
	
	String password;
	
	Father f;
	
	public User(String password,Father f){
		this.password = password;
		this.f = f;
	}

	/**
	 * 方法1，深复制
	 */
	protected Object deepClone() throws CloneNotSupportedException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.flush();
			byte[] bs = baos.toByteArray();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bs);
			ois = new ObjectInputStream(bais);
			Object obj = ois.readObject();
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
				try {
					if(null != oos){
						oos.close();
					}
					if(null != ois){
						ois.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 方法2，浅复制
	 */
	@Override
	protected User clone() throws CloneNotSupportedException{
		return (User) super.clone();
	}

}

/*
 * Project: mybatis-demo
 * 
 * File Created at 2013年12月3日 下午12:22:51
 * 
 * Copyright 2012 seaway.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Seaway Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with seaway.com.
 */
package org.liu.demo.mybatis;

import java.io.Serializable;

public class User implements Serializable{
    
    private static final long serialVersionUID = 7720456466418717052L;
    
    private int id;
    private String userName;
    private String password;
    private String age;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    
}

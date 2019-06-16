/*
 * Project: mybatis-demo
 * 
 * File Created at 2013年12月3日 下午12:21:09
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

import java.util.List;

/**
 * TODO：类型描述
 * <br>----------------------------------------------------变更记录--------------------------------------------------
 * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
 * <br> 0     | 2013年12月3日 下午12:21:09  	|  刘章盛     | 创建  
 */
public interface UserDao {
    
    public int insert(User user);
    
    public int update(User user);
    
    public int delete(String userName);
    
    public List<User> selectAll();
    
    public int countAll();
    
    public User findByUserName(String userName);

}

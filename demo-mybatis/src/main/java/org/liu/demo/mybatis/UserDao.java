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

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO：类型描述
 * <br>----------------------------------------------------变更记录--------------------------------------------------
 * <br> 序号      |           时间                        	|   作者      |                          描述
 * <br> 0     | 2013年12月3日 下午12:21:09  	|  刘章盛     | 创建
 */
public interface UserDao {

    int insert(User user);

    int update(User user);

    int delete(String userName);

    List<User> selectAll();

    List<User> listByAge(Integer age);

    Page<User> listByAge(Page<User> page, Integer age);

    int countAll();

    User findByUserName(String userName);

    List<User> testForeach(@Param("nameList") List<String> nameList);

    User findById(Integer id);
}

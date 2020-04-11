package org.liu.demo.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(inputStream);
        inputStream.close();
    }

    @After
    public void destroy(){

    }

    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findById(1);
        System.out.println(user);

        System.out.println("-----------update user-------------");
        user.setAge(99);
        userDao.update(user);
        sqlSession.close();

        System.out.println("----------------第二次查询------------");
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findById(1);
        System.out.println(user1);

        System.out.println(user == user1);

    }

}

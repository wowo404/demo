package org.liu.demo.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.List;

public class MybatisTest extends BaseTest {

    @Test
    public void testSave() throws ParseException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        WKTReader wktReader = new WKTReader();
        Point point = (Point) wktReader.read("point(114.94149 25.823457)");

        User user = new User();
        user.setUsername("d");
        user.setPassword("123456");
        user.setAge(11);
        user.setEnabled(1);
        user.setGis(point);
        user.setStatus("online");
        userDao.insert(user);
        System.out.println(user.getId());
        sqlSession.commit();
    }

    @Test
    public void testQuery() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.selectAll();
        for (User user : users) {
            System.out.println(user.getGis().toText());
            System.out.println(user.getGis().getCoordinate().getX() + " -- " + user.getGis().getCoordinate().getY());
        }
        users.forEach(System.out::println);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testSecondLevelCache() {
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

    @Test
    public void testListByAge() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.listByAge(1);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testPageListByAge() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Page<User> page = new Page<>();
        List<User> users = userDao.listByAge(1);
        for (User user : users) {
            System.out.println(user);
        }
    }

}

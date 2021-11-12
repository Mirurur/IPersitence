package com.amateur;

import com.amateur.io.Resource;
import com.amateur.sqlsession.SqlSession;
import com.amateur.sqlsession.SqlSessionFactory;
import com.amateur.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/12 16:04
 */

public class TestMapper {
    public static void main(String[] args) throws Exception{
        InputStream resourceAsStream = Resource.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId(1L);
        User user1 = mapper.findById(user);
        user.setId(1L);
        User user2 = mapper.findById(user);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }
}

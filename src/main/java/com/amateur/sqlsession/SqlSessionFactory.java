package com.amateur.sqlsession;

/**
 * @author yeyu
 * @date 2021/11/10 17:54
 */
public interface SqlSessionFactory {

    /**
     * 创建sqlSession对象
     * @return
     */
    SqlSession openSession();
}

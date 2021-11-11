package com.amateur.sqlsession;

import com.amateur.pojo.Configuration;

/**
 * @author yeyu
 * @date 2021/11/11 9:35
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}

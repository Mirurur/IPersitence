package com.amateur.sqlsession;

import com.amateur.pojo.Configuration;
import com.amateur.pojo.MappedStatement;

import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/11 10:03
 */
public interface Executor {

    /**
     * sql语句执行器
     *
     * @param configuration   数据库配置信息
     * @param mappedStatement sql语句信息
     * @param params          可变参数
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}

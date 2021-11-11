package com.amateur.sqlsession;

import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/11 9:37
 */
public interface SqlSession {
    /**
     * 列表查询
     *
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    /**
     * 查询单个
     *
     * @param statementId
     * @param <E>
     * @param params
     * @return
     * @throws Exception
     */
    <E> E selectOne(String statementId, Object... params) throws Exception;

    /**
     * 生成代理类
     *
     * @param mapperClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<?> mapperClass);
}

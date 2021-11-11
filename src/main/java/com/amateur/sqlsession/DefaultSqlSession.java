package com.amateur.sqlsession;

import com.amateur.pojo.Configuration;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/11 9:38
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        return simpleExecutor.query(configuration, configuration.getMappedStatementMap().get(statementId), params);
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<T> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("返回结果不正确");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxy = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + name;
                // 获取被调用方法的返回类型
                Type returnType = method.getGenericReturnType();
                // 判断返回值类型是否有泛型
                if (returnType instanceof ParameterizedType) {
                    return selectList(statementId,args);
                } else {
                    return selectOne(statementId,args);
                }
            }
        });
        return (T)proxy;
    }


}

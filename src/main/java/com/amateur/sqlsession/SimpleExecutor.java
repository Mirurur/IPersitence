package com.amateur.sqlsession;

import com.amateur.config.BoundSql;
import com.amateur.pojo.Configuration;
import com.amateur.pojo.MappedStatement;
import com.amateur.utils.GenericTokenParser;
import com.amateur.utils.ParameterMapping;
import com.amateur.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/11 10:05
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception{
        // 1.注册驱动 获取数据库连接
        Connection connection = configuration.getDataSource().getConnection();
        // 2.获取sql语句 select * from user where id = #{id}
        String sql = mappedStatement.getSql();
        // 3.转换sql语句 占位符替换 select * from user where id = ?
        BoundSql boundSql = getBoundSql(sql);
        // 4.获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        // 5.设置参数
        // 获取参数全路径类名
        String resultType = mappedStatement.getResultType();
        Class<?> resultClass = getClassType(resultType);
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            // 反射
            Field field = parameterClass.getDeclaredField(content);
            field.setAccessible(true);
            Object o = field.get(params[0]);
            preparedStatement.setObject(i+1,o);
        }
        // 6.执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        // 封装返回结果集
        List<E> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Object resultObj = resultClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段值
                Object value = resultSet.getObject(columnName);
                // 使用反射，映射表与实体类之间的关系
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(resultObj, value);
            }
            resultList.add((E) resultObj);
        }
        return resultList;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType!=null) {
            return Class.forName(parameterType);
        }
        return null;
    }

    private BoundSql getBoundSql(String sql) {
        // ? 代替#{}
        // 标记处理类，完成对占位符的解析
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        // 标记解析器
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 解析后的sql
        String parseSql = genericTokenParser.parse(sql);
        // 解析后的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parseSql,parameterMappings);
    }
}

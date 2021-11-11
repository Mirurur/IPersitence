package com.amateur.sqlsession;

import com.amateur.config.XmlConfigBuilder;
import com.amateur.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author yeyu
 * @date 2021/11/10 17:53
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        // 使用dom4j解析配置文件,封装成Configuration对象
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);
        // 创建SqlSessionFactory对象
        return new DefaultSqlSessionFactory(configuration);
    }
}

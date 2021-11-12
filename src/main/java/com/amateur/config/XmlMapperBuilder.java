package com.amateur.config;

import com.amateur.pojo.Configuration;
import com.amateur.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author yeyu
 * @Description
 * @date 2021-11-10 21:31
 */
public class XmlMapperBuilder {
    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.selectNodes("//select");
        list.forEach(element ->  {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String useCache = element.attributeValue("useCache");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setUseCache(useCache==null||"true".equalsIgnoreCase(useCache));
            mappedStatement.setId(id);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setResultType(resultType);
            String key = namespace + "." +id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        });
    }
}

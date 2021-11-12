package com.amateur.pojo;

import com.amateur.cache.Cache;
import com.amateur.sqlsession.CacheExecutor;
import com.amateur.sqlsession.SimpleExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyu
 * @date 2021/11/10 17:48
 * 数据库信息解析对象
 */
public class Configuration {
    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * key: statementId
     * value: 封装好的mappedStatement对象
     */
    private Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

    private CacheExecutor cacheExecutor;

    public synchronized CacheExecutor newCacheExecutor() {
        return new CacheExecutor(new SimpleExecutor());
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

}

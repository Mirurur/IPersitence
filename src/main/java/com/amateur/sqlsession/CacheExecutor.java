package com.amateur.sqlsession;

import com.amateur.cache.Cache;
import com.amateur.cache.CacheKey;
import com.amateur.pojo.Configuration;
import com.amateur.pojo.MappedStatement;

import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/16 9:26
 */
public class CacheExecutor implements Executor {

    private final Executor simpleExecutor;

    public CacheExecutor(Executor executor) {
        this.simpleExecutor = executor;
    }

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        Cache cache = mappedStatement.getCache();
        CacheKey cacheKey = CacheKey.createdCacheKey(mappedStatement, params);
        if (cache.getObject(cacheKey) != null) {
            return (List<E>) cache.getObject(cacheKey);
        } else {
            List<E> objectList = simpleExecutor.query(configuration, mappedStatement, params);
            cache.putObject(cacheKey,objectList);
            return objectList;
        }
    }
}

package com.amateur.sqlsession;

import com.amateur.cache.Cache;
import com.amateur.cache.CacheKey;
import com.amateur.pojo.Configuration;
import com.amateur.pojo.MappedStatement;

import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/12 15:41
 */
public class CacheExecutor implements Executor {

    private final Cache cache = new Cache();

    private final SimpleExecutor executor;

    public CacheExecutor(SimpleExecutor executor) {
        this.executor = executor;
    }

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        CacheKey cacheKey = CacheKey.createdCacheKey(mappedStatement,params);
        if (cache.getObject(cacheKey) != null) {
            return (List<E>) cache.getObject(cacheKey);
        } else {
            List<E> objects = executor.query(configuration, mappedStatement, params);
            cache.putObject(cacheKey,objects);
            return objects;
        }
    }
}

package com.amateur.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyu
 * @date 2021/11/12 15:38
 */
public class Cache {

    private final Map<CacheKey,Object> map = new HashMap<>();

    public void putObject(CacheKey cacheKey,Object o) {
        map.put(cacheKey, o);
    }

    public void clear() {
        map.clear();
    }

    public Object getObject(CacheKey cacheKey) {
        return map.get(cacheKey);
    }
}

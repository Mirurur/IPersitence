package com.amateur.cache;

import com.amateur.pojo.MappedStatement;
import com.amateur.utils.ParameterMapping;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yeyu
 * @date 2021/11/12 15:38
 */
public class CacheKey {
    private String statementId;

    private String sqlText;

    private Map<String,String> params;

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public static CacheKey createdCacheKey(MappedStatement mappedStatement, Object... params) {
        CacheKey cacheKey = new CacheKey();
        cacheKey.setSqlText(mappedStatement.getSql());
        cacheKey.setStatementId(mappedStatement.getId());
        if (params!=null && params.length >=1) {
            Object param = params[0];
            Map<String, String> map = new HashMap<>();
            for (Field field : param.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    map.put(field.getName(), field.get(param)==null?"":field.get(param).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            cacheKey.setParams(map);
        }
        return cacheKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CacheKey cacheKey = (CacheKey) o;

        Map<String, String> params = cacheKey.getParams();
        if (params!=null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                if (!this.getParams().get(key).equals(params.get(key))) {
                    return false;
                }
            }
        }
        return Objects.equals(statementId, cacheKey.statementId) && Objects.equals(sqlText, cacheKey.sqlText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statementId, sqlText,params);
    }
}

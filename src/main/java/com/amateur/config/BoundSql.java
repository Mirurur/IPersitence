package com.amateur.config;

import com.amateur.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeyu
 * @date 2021/11/11 10:14
 */
public class BoundSql {
    /**
     * 解析过后的sql
     */
    private String sqlText;

    /**
     * 参数映射集合
     */
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }

    public BoundSql() {}

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }
}

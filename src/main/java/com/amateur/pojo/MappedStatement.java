package com.amateur.pojo;

/**
 * @author yeyu
 * @date 2021/11/10 17:46
 * mapper.xml 解析对象
 */
public class MappedStatement {
    /**
     * id标识
     */
    private String id;
    /**
     * 返回值类型
     */
    private String resultType;
    /**
     * 参数值类型
     */
    private String parameterType;
    /**
     * sql
     */
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

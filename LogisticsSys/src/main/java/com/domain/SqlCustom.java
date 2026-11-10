package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Sql的对象，包含了sql语句和参数
 */
public class SqlCustom {
    public SqlCustom() {
        condition = new ArrayList<String>();

    }

    public SqlCustom(String sql, List<String> condition) {
        this.setSql(sql);
        this.setCondition(condition);

    }


    private String sql;
    private List<String> condition;

    //获取参数
    public List<String> getCondition() {
        return condition;
    }

    private void setCondition(List<String> condition) {
        this.condition = condition;
    }

    //获取Sql
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

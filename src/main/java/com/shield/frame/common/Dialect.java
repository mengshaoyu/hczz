package com.shield.frame.common;

/**
 * Dialect 抽象类
 */
public abstract class Dialect {

    public static enum Type {
        MYSQL, ORACLE
    }

    public abstract String getLimitString(String sql, int startRow, int pageSize);

}

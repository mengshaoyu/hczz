package com.shield.frame.common;

/**
 * Oracle分页Dialect类
 */
public class OracleDialect extends Dialect {

    @Override
    public String getLimitString(String sql, int startRow, int pageSize) {

        sql = sql.trim();
        StringBuffer sb = new StringBuffer(sql.length() + 50);
        sb.append("SELECT * FROM (SELECT A.*, ROWNUM as RN_ FROM (");
        sb.append(sql);
        sb.append(" )A )b WHERE b.RN_ <");
        sb.append(startRow + pageSize);
        sb.append(" and b.RN_ >=");
        if (startRow >= 0) {
            sb.append(startRow);
        }

        return sb.toString();
    }

}

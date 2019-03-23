package com.shield.frame.common;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PaginationInterceptor
 */

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

    static final Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,
            SystemMetaObject.DEFAULT_OBJECT_FACTORY,
            SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) metaStatementHandler
            .getValue("delegate.parameterHandler");
        Map parameterMap = (Map) defaultParameterHandler.getParameterObject();
        Object sidx = parameterMap.get("_sidx");
        Object sord = parameterMap.get("_sord");

        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        if (sidx != null && sord != null) {
            originalSql = originalSql + " order by " + sidx + " " + sord;
        }

        Configuration configuration = (Configuration) metaStatementHandler
            .getValue("delegate.configuration");

        Dialect.Type databaseType = null;
        try {
            databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect")
                .toUpperCase());
        }
        catch (Exception e) {
            // ignore
        }
        if (databaseType == null) {
            throw new RuntimeException(
                "the value of the dialect property in configuration.xml is not defined : "
                    + configuration.getVariables().getProperty("dialect"));
        }
        Dialect dialect = null;
        switch (databaseType) {
        case ORACLE:
            dialect = new OracleDialect();
            break;
        case MYSQL:// 需要实现MySQL的分页逻辑
            break;

        }

        metaStatementHandler.setValue("delegate.boundSql.sql",
            dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        if (log.isDebugEnabled()) {
            BoundSql boundSql = statementHandler.getBoundSql();
            log.debug("生成分页SQL : " + boundSql.getSql());
        }
        return invocation.proceed();

    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties arg0) {
    }

}

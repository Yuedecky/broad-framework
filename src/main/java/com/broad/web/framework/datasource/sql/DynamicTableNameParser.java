package com.broad.web.framework.datasource.sql;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.broad.web.framework.context.CommonContextHolder;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Collection;
import java.util.Map;

/**
 * @author broad
 * @date 20200111
 **/
public class DynamicTableNameParser implements ISqlParser {

    private Map<String, ITableNameHandler> tableNameHandlerMap;

    private ITableNameHandler defaultTableNameHandler = (metaObject, sql, tableName) -> {
        String tenantCode = CommonContextHolder.getTenant();
        if (StrUtil.isEmpty(tenantCode)) {
            return tableName;
        }
        return CommonContextHolder.getDatabase(tenantCode) + StrUtil.DOT + tableName;
    };

    @Override
    public SqlInfo parser(MetaObject metaObject, String sql) {
        if (allowProcess(metaObject)) {
            Collection<String> tables = new TableNameParser(sql).tables();
            if (CollectionUtil.isNotEmpty(tables)) {
                boolean sqlParsed = false;
                String parsedSql = sql;
                for (final String table : tables) {
                    ITableNameHandler tableNameHandler = defaultTableNameHandler;
                    if (CollectionUtil.isNotEmpty(tableNameHandlerMap)) {
                        tableNameHandler = tableNameHandlerMap.get(table);
                    }
                    if (tableNameHandler != null) {
                        parsedSql = tableNameHandler.process(metaObject, parsedSql, table);
                        sqlParsed = true;
                    }
                }
                if (sqlParsed) {
                    return SqlInfo.newInstance().setSql(parsedSql);
                }
            }

            String tenantCode = CommonContextHolder.getTenant();
            if (StrUtil.isEmpty(tenantCode)) {
                return null;
            }

            MultiTenantInterceptor multiTenantInterceptor = new MultiTenantInterceptor();
            multiTenantInterceptor.setSchemaName(CommonContextHolder.getDatabase(tenantCode));
            String parsedSql = multiTenantInterceptor.processSqlByInterceptor(sql);
            return SqlInfo.newInstance().setSql(parsedSql);
        }
        return null;
    }


    /**
     * 判断是否允许执行
     * <p>例如：逻辑删除只解析 delete , update 操作</p>
     *
     * @param metaObject 元对象
     * @return true
     */
    private boolean allowProcess(MetaObject metaObject) {
        return true;
    }
}

package com.broad.web.framework.datasource.sql;

import com.broad.web.framework.utils.DateUtils;
import com.broad.web.framework.utils.FieldUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author broad
 * update时自动更新记录行的更新时间
 * 注意：sql中尽量不要用"set"、"update"作为字段或结果集的别名（待优化）
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class UpdateTimeInterceptor implements Interceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(UpdateTimeInterceptor.class);

    private static final String UPDATE_TIME_FIELD = "updated_time";
    private static final Pattern UPDATE_PATTERN = Pattern.compile("(?i)(^| |\n)update[ \n]+(\\w+)");
    private static final String SET_PATTERN_STR = "[ \n][sS][eE][tT][ \n]";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        Connection connection = (Connection) invocation.getArgs()[0];
        String newSql = fillSql(boundSql.getSql(), connection);
        if (Objects.nonNull(newSql)) {
            FieldUtils.setFieldValue(boundSql, "sql", newSql);
        }
        return invocation.proceed();
    }

    /**
     *
     * @param sql
     * @param connection
     * @return
     */
    private String fillSql(String sql, Connection connection) {
        if (sql == null) {
            return null;
        }
        Matcher matcher = UPDATE_PATTERN.matcher(sql);
        if (matcher.find()) {
            String tbName = matcher.group(2);
            List<String> tbFields = getTbFields(tbName, connection);
            if (CollectionUtils.isEmpty(tbFields)) {
                return null;
            }
            // 更新update time
            if (tbFields.contains(UPDATE_TIME_FIELD)) {
                sql = sql.replaceFirst(SET_PATTERN_STR, " SET " + UPDATE_TIME_FIELD + " = '" + DateUtils.now() + "', ");
            }
            return sql;
        }
        return null;
    }

    private List<String> getTbFields(String tbName, Connection connection) {
        List<String> fields = new ArrayList<>();
        if (StringUtils.isEmpty(tbName)) {
            return fields;
        }
        String selectSql = String.format("select column_name from information_schema.columns where table_name = '%s'", tbName);
        try (PreparedStatement pstmt = connection.prepareStatement(selectSql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                fields.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("获取表字段列表失败：{}", e.getMessage());
        }
        return fields;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

}

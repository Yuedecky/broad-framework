package com.broad.web.framework.datasource.sql;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.broad.web.framework.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.context.CommonContextHolder;
import com.broad.web.framework.utils.LoginUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.apache.ibatis.mapping.SqlCommandType.*;

/**
 * @author broad
 * @date 20200111
 * 该拦截器只用于demo环境， 开发和生产都不需要
 **/
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class WriteSqlInterceptor extends AbstractSqlParserHandler implements Interceptor {


    private static final List<String> BUILD_IN_TENANTS = Arrays.asList("0000", "0001");

    private static final List<String> BUILD_IN_USERS = Arrays.asList("10001", "10002");

    private static final String[] ALLOW_PERMIT_OPERATIONS = new String[]{"sms", "log", "xxl"};

    @Override
    @SneakyThrows
    public Object intercept(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        sqlParser(metaObject);
        // 读操作 放行
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }
        // 记录日志相关的 放行
        if (StrUtil.containsAnyIgnoreCase(mappedStatement.getId(),
                ALLOW_PERMIT_OPERATIONS)) {
            return invocation.proceed();
        }
        // userId=1 的超级管理员 放行
        String userCode = LoginUtils.currentUserCode();
        String tenant = CommonContextHolder.getTenant();
        log.warn("mapperId=[{}], userCode=[{}]", mappedStatement.getId(), userCode);


        //演示用的超级管理员 能查 和 增
        if (BUILD_IN_USERS.contains(userCode) && (DELETE.equals(mappedStatement.getSqlCommandType()))) {
            throw new BaseException(ReturnCode.NO_PERMISSION);
        }

        //内置的租户 不能写入
        boolean isWrite = CollectionUtil.contains(Arrays.asList(DELETE, INSERT, UPDATE), mappedStatement.getSqlCommandType());
        if (BUILD_IN_TENANTS.contains(tenant) && isWrite) {
            throw new BaseException(ReturnCode.NO_PERMISSION);
        }

        // 你还可以自定义其他限制规则， 比如：IP 等

        //其他用户
        return invocation.proceed();
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }
}

package com.broad.web.framework.datasource;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author broad
 */
@Configuration
public class BaseDataSourceConfigure  {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;


    /**
     * common
     */
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${spring.datasource.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    @Value("${spring.datasource.logAbandoned}")
    private boolean logAbandoned;
    @Value("${spring.datasource.filters}")
    private String proxyFilters;
    @Value("${spring.datasource.slowSqlMillis}")
    private int slowSqlMillis;
    @Value("${spring.datasource.logSlowSql}")
    private boolean logSlowSql;
    @Value("${spring.datasource.mergeSql}")
    private boolean mergeSql;
    @Value("${spring.datasource.loginDruidUsername}")
    private String loginDruidUsername;
    @Value("${spring.datasource.loginDruidPassword}")
    private String loginDruidPassword;
    @Value("${spring.datasource.logViolation}")
    private boolean logViolation;
    @Value("${spring.datasource.throwException}")
    private boolean throwException;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        final DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(Base64Decoder.decodeStr(password));
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMinIdle(this.minIdle);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setMaxWait(this.maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(this.validationQuery);
        dataSource.setTestWhileIdle(this.testWhileIdle);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setTestOnReturn(this.testOnReturn);
        dataSource.setPoolPreparedStatements(this.poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
        dataSource.setRemoveAbandoned(this.removeAbandoned);
        dataSource.setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
        dataSource.setLogAbandoned(this.logAbandoned);
        List<Filter> proxyFilterList = getDruidFilterList(this.proxyFilters);
        if (!CollectionUtils.isEmpty(proxyFilterList)) {
            dataSource.setProxyFilters(proxyFilterList);
        }
        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DruidDataSource thirdPartyDataSource) {
        return new DataSourceTransactionManager(thirdPartyDataSource);
    }


    private List<Filter> getDruidFilterList(String proxyFilters) {
        List<Filter> filterList = Lists.newArrayList();
        if (!StringUtils.isEmpty(proxyFilters)) {
            List<String> filters = Splitter.on(",").splitToList(proxyFilters);
            filters.forEach(
                    it -> {
                        if(it.equals("wall")){
                            filterList.add(new WallFilter());
                        }
                        if(it.equals("stat")){
                            filterList.add(new StatFilter());
                        }
                        if(it.equals("log4j")){
                            filterList.add(new Log4jFilter());
                        }
                    }
            );
        }
        return filterList;
    }
}

package com.broad.web.framework.cache;

import com.broad.web.framework.cache.util.SpringJ2CacheConfigUtil;
import com.broad.web.framework.cache.util.SpringUtil;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.StandardEnvironment;


/**
 * @author broad
 * @date 20200111
 **/
@ConditionalOnClass(J2Cache.class)
@EnableConfigurationProperties({J2CacheConfig.class})
@Configuration
@PropertySource(value = "${j2cache.config-location}", encoding = "UTF-8", ignoreResourceNotFound = true)
public class J2CacheAutoConfiguration {


    @Autowired
    private StandardEnvironment standardEnvironment;

    @Bean("j2CacheConfig")
    public J2CacheConfig j2CacheConfig() {
        J2CacheConfig cacheConfig = SpringJ2CacheConfigUtil.initFromConfig(standardEnvironment);
        return cacheConfig;
    }

    @Bean
    @DependsOn({"springUtil", "j2CacheConfig"})
    public CacheChannel cacheChannel(J2CacheConfig j2CacheConfig)  {
        J2CacheBuilder builder = J2CacheBuilder.init(j2CacheConfig);
        return builder.getChannel();
    }

    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }
}

package com.broad.web.framework.config.security;

import com.broad.web.framework.bean.AuthProperties;
import com.broad.web.framework.request.RequestAttributeHystrixConcurrencyStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author broad
 */
@Configuration
@EnableConfigurationProperties(value = AuthProperties.class)
@Slf4j
public class AutoSecurityConfiguration {


    public AutoSecurityConfiguration() {
        log.info("autoSecurityConfiguration started");
    }


    @Bean
    public ServiceFeignInterceptor feignInterceptor() {
        return new ServiceFeignInterceptor();
    }

    @Bean
    public RequestAttributeHystrixConcurrencyStrategy requestAttributeHystrixConcurrencyStrategy() {
        return new RequestAttributeHystrixConcurrencyStrategy();
    }



}

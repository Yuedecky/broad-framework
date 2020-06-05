package com.broad.web.framework.config.template;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RestTemplateConfig {

    @Resource
    private TestRequestInterceptor requestInterceptor;

    @Bean
    public TestRestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder().interceptors(requestInterceptor);
        return new TestRestTemplate(builder);
    }
}

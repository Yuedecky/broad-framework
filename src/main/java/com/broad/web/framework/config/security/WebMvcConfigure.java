package com.broad.web.framework.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author broad
 */
@Slf4j
@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

    public WebMvcConfigure() {
        log.info("WebMvcConfigure init...");
    }

    @Value("${broad.auth.require:true}")
    private Boolean require;

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public UserAuthInterceptor userAuthInterceptor() {
        return new UserAuthInterceptor();
    }

    /**
     * 添加跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的路径
        registry.addMapping("/**")
                // 允许跨域访问的源
                .allowedOrigins("*")
                // 允许请求方法
                .allowedMethods("*")
                // 预检间隔时间
                .maxAge(168000)
                // 允许头部设置
                .allowedHeaders("*")
                // 是否发送cookie
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {"/v2/api-docs", "/swagger-resources/**", "/favicon.ico", "/static/**", "/api/**",
                "/swagger-ui.html", "/swagger-ui.html/**", "/webjars/**", "/error"};
        Collections.addAll(list, urls);
        return list;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPatterns = getExcludeCommonPathPatterns();
        log.info("excludePathPatterns:{}", commonPatterns);
        if (require) {
            registry.addInterceptor(userAuthInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPatterns);
        }
    }

}

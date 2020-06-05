package com.broad.web.framework.config.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.bean.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Map;

/**
 * @author broad
 */
@Slf4j
public class ServiceFeignInterceptor implements RequestInterceptor {

    @Autowired
    private AuthProperties authProperties;


    public ServiceFeignInterceptor() {
        log.info("Feign interceptor init...");
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String bodyTemplate = requestTemplate.bodyTemplate();
        Map<String, Collection<String>> headers = requestTemplate.headers();
        String method = requestTemplate.method();
        String url = requestTemplate.url();
        log.warn("发送feign请求,body:{},header:{},method:{},url:{}", bodyTemplate, headers, method, url);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.warn("feign attributes is null!!!");
            return;
        }
        String token = attributes.getRequest().getHeader(authProperties.getTokenHeader());
        log.warn("服务之间feign调用时，请求的token头 {} ，token值 {}", authProperties.getTokenHeader(), token);
        //这里目的是，服务之间调用时，以为每个服务都启用了token验证机制，所以在feign请求也要加上token
        requestTemplate.header(authProperties.getTokenHeader(), token);
    }
}

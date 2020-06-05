package com.broad.web.framework.config.feign;

import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author broad
 */
@Configuration
@Slf4j
public class FeignConfiguration {

    public FeignConfiguration() {
        log.info("Feign configuration init...");
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new SimpleErrorDecoder();
    }
}

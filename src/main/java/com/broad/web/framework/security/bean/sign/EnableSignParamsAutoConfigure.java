package com.broad.web.framework.security.bean.sign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(value = SignProperties.class)
@Component
@Slf4j
public class EnableSignParamsAutoConfigure {

    public EnableSignParamsAutoConfigure(){
        log.info("Current project enable sign method params,beginning auto configure...");
    }


}

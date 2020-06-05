package com.broad.web.framework.security.bean.sign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 签名的方式 根据请求携带的 timestamp + token ? + params 做 配置算法的 加密
 *
 */
@ConfigurationProperties(prefix = "broad.sign")
@Data
public class SignProperties {

    private String algorithm ;

    private Long expireInSeconds;

    private String useHeader;

    private String headerKey;


}

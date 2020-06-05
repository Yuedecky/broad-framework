package com.broad.web.framework.bean;

/**
 *
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.user")
@Data
public class AuthProperties {

    private String tokenHeader;

    private boolean enable;

    private int expire;

    private AuthProperties.PubKey pubKey;

    private AuthProperties.PriKey priKey;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PubKey {
        private String path;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PriKey {
        private String path;
    }


}

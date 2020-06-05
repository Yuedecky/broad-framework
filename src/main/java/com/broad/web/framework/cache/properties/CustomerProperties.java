package com.broad.web.framework.cache.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Map;

/**
 * @author broad
 * @date 20200111
 **/
@Data
@ConfigurationProperties(prefix = "broad.cache")
public class CustomerProperties {
    /**
     * 目前只支持 redis
     * 生产环境请用redis！！！
     */
    private CacheType type = CacheType.REDIS;

    /**
     * 全局配置
     */
    private Cache def = new Cache();

    /**
     * 针对某几个具体的key特殊配置
     *
     * 改属性只对 redis 有效！！！
     * configs的key需要配置成@Cacheable注解的value
     */
    private Map<String, Cache> configs;

    @Data
    public static class Cache {

        /**
         * key 的过期时间
         * 默认1天过期
         * eg:
         * timeToLive: 1d
         */
        private Duration timeToLive = Duration.ofMinutes(10);

        /**
         * 是否允许缓存null值
         */
        private boolean cacheNullValues = true;

        /**
         * key 的前缀
         * 最后的key格式： keyPrefix + @Cacheable.value + @Cacheable.key
         *
         * 使用场景： 开发/测试环境 或者 演示/生产 环境，为了节省资源，往往会共用一个redis，即可以根据key前缀来区分(当然，也能用切换 database 来实现)
         */
        private String keyPrefix;

        /**
         * 写入redis时，是否使用key前缀
         */
        private boolean useKeyPrefix = true;

        /**
         * Caffeine 的最大缓存个数
         */
        private int maxSize = 1000;


    }

}

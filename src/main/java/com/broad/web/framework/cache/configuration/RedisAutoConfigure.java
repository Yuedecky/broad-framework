package com.broad.web.framework.cache.configuration;

import com.broad.web.framework.cache.properties.CustomerProperties;
import com.broad.web.framework.cache.serialize.RedisObjectSerializer;
import com.broad.web.framework.tool.StrPool;
import com.google.common.collect.Maps;
import com.broad.web.framework.cache.lock.DistributedLock;
import com.broad.web.framework.cache.lock.RedisDistributedLock;
import com.broad.web.framework.cache.repository.CacheFactory;
import com.broad.web.framework.cache.repository.RedisRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author broad
 * @date 20200111
 **/
@ConditionalOnClass(RedisConnectionFactory.class)
@ConditionalOnProperty(name = "broad.cache.type", havingValue = "REDIS", matchIfMissing = true)
@EnableConfigurationProperties({RedisProperties.class, CustomerProperties.class})
public class RedisAutoConfigure {

    @Autowired
    private CustomerProperties cacheProperties;

    /**
     * 分布式锁
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DistributedLock RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        return new RedisDistributedLock(redisTemplate);
    }

    /**
     * RedisTemplate配置
     *
     * @param factory
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(redisObjectSerializer);
        redisTemplate.setValueSerializer(redisObjectSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * redis 持久库
     *
     * @param redisTemplate the redis template
     * @return the redis repository
     */
    @Bean
    @ConditionalOnMissingBean
    public CacheFactory redisRepository(RedisTemplate<String, Object> redisTemplate) {
        return new RedisRepositoryImpl(redisTemplate);
    }

    /**
     * 用于 @Cacheable 相关注解
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "cacheManager")
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defConfig = getDefConf();
        defConfig.entryTtl(cacheProperties.getDef().getTimeToLive());

        Map<String, CustomerProperties.Cache> configs = cacheProperties.getConfigs();
        Map<String, RedisCacheConfiguration> map = Maps.newHashMap();
        //自定义的缓存过期时间配置
        Optional.ofNullable(configs).ifPresent((config) -> {
            config.forEach((key, cache) -> {
                RedisCacheConfiguration cfg = handleRedisCacheConfiguration(cache, defConfig);
                map.put(key, cfg);
            });
        });

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defConfig)
                .withInitialCacheConfigurations(map)
                .build();
    }

    private RedisCacheConfiguration getDefConf() {
        RedisCacheConfiguration def = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new RedisObjectSerializer()));
        return handleRedisCacheConfiguration(cacheProperties.getDef(), def);
    }

    private RedisCacheConfiguration handleRedisCacheConfiguration(CustomerProperties.Cache redisProperties, RedisCacheConfiguration config) {
        if (Objects.isNull(redisProperties)) {
            return config;
        }
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.computePrefixWith(cacheName -> redisProperties.getKeyPrefix().concat(StrPool.COLON).concat(cacheName).concat(StrPool.COLON));
        } else {
            config = config.computePrefixWith(cacheName -> cacheName.concat(StrPool.COLON));
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        return config;
    }
}

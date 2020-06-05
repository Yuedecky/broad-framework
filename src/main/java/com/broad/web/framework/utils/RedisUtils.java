package com.broad.web.framework.utils;


import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 */
public class RedisUtils {
    // 在Redis配置初始化完后注入
    public static RedisTemplate<String, Object> redisTemplate;

    private static final String KEY_ACCESS_TOKEN = "keyAccessToken";
    private static final String KEY_GLOBAL_ACCESS_TOKEN = "keyGlobalAccessToken";
    private static final String KEY_JS_API_TICKET = "keyJsApiTicket";
    private static final String KEY_WEB_AUTH_ACCESS_TOKEN = "keyWebAuthAccessToken";
    private static final String PREFIX_SESSION_KEY = "snKey_";

    /**
     * 注入redis template
     */
    public static void autowireRedisTemplate(RedisTemplate<String, Object> _redisTemplate) {
        redisTemplate = _redisTemplate;
        //设为String key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }

    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public static void saveObject(String key, Object o) {
        ValueOperations<String, Object> valueOp = redisTemplate.opsForValue();
        valueOp.set(key, o);
    }

    public static void saveObject(String key, Object o, long expiresIn) {
        ValueOperations<String, Object> valueOp = redisTemplate.opsForValue();
        valueOp.set(key, o, expiresIn, TimeUnit.SECONDS);
    }

    public static Object getObject(String key) {
        if (Objects.isNull(key)) {
            return null;
        }
        ValueOperations<String, Object> valueOp = redisTemplate.opsForValue();
        return valueOp.get(key);
    }

	@SuppressWarnings("unchecked")
	public static List<String> getStringList(String key) {
        if (Objects.isNull(key)) {
            return null;
        }
        ValueOperations<String, Object> valueOp = redisTemplate.opsForValue();
        Object o = valueOp.get(key);
        if (o == null) {
            return null;
        }
        if (o instanceof Object[]) {
            Object[] objArr = (Object[]) o;
            if (objArr.length > 0) {
                List<String> stringList = new ArrayList<>();
                for (Object obj : objArr) {
                    if (obj != null) {
                        stringList.add((String) obj);
                    }
                }
                return stringList;
            } else {
                return null;
            }
        } else if (o instanceof ArrayList) {
            return (List<String>) o;
        }else {
            throw new IllegalArgumentException("object类型非法");
        }
    }

    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 左匹配方式删除keys
     */
    public static void deleteByLeftPattern(String cacheName, String... params) {
        String keyPrefix = cacheName + "::#";
        String pattern = keyPrefix + concatParamsForKey(params) + "*";
        scanAndDel(pattern);
    }

    /**
     * 全匹配方式删除keys
     */
    public static void deleteByFullPattern(String cacheName, String... params) {
        String keyPrefix = cacheName + "::#*";
        String pattern = keyPrefix + concatParamsForKey(params) + "*";
        scanAndDel(pattern);
    }

    public static void scanAndDel(String pattern) {
        RedisConnection connection = null;
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        try {
            connection = factory.getConnection();
            ScanOptions options = ScanOptions.scanOptions().match(pattern).count(Integer.MAX_VALUE).build();
            Cursor<?> c = connection.scan(options);
            while (c.hasNext()) {
                connection.del((byte[]) c.next());
            }
        }finally {
            // 执行完释放连接
            RedisConnectionUtils.releaseConnection(connection, factory);
        }
    }

    public static String concatParamsForKey(Object[] objects) {
        StringBuilder sb = new StringBuilder(32);
        String sp = "";
        for (Object object : objects) {
            sb.append(sp);
            if (object == null) {
                sb.append("NULL");
            } else {
                sb.append(object.toString());
            }
            sp = ".";
        }
        return sb.toString();
    }

}

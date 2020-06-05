package com.broad.web.framework.cache.serialize;

import net.oschina.j2cache.util.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

/**
 * @author broad
 * @date 20200111
 **/
public class J2CacheSerializer implements RedisSerializer<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(J2CacheSerializer.class);

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        try {
            return SerializationUtils.serialize(t);
        } catch (IOException e) {
            LOGGER.error("[{}] 序列化对象失败：[{}]", this.getClass().getCanonicalName(), e);
        }
        return null;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            return SerializationUtils.deserialize(bytes);
        } catch (IOException e) {
            LOGGER.error("[{}]反序列化失败：[{}]", this.getClass().getCanonicalName(), e);
        }
        return null;
    }
}

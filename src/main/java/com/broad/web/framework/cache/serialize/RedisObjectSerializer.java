package com.broad.web.framework.cache.serialize;

import com.broad.web.framework.converter.EnumDeserializer;
import com.broad.web.framework.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author broad
 * @date 20200111
 **/
public class RedisObjectSerializer extends Jackson2JsonRedisSerializer<Object> {

    public RedisObjectSerializer() {
        super(Object.class);
        ObjectMapper om = new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                //日期格式
                .setDateFormat(new SimpleDateFormat(DateUtils.PATTERN_YMDHMS));

        SimpleModule simpleModule = new SimpleModule()
                .addDeserializer(Enum.class, EnumDeserializer.INSTANCE)
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtils.PATTERN_YMDHMS)))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtils.PATTERN_YMDHMS)));
        om.registerModule(simpleModule);
        this.setObjectMapper(om);
    }
}

package com.broad.web.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author broad
 */
public class JsonUtils {

    /**
     * The Constant MAPPER.
     */
    public final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        // 遇到未知field不报错
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    private static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 序列化
     *
     * @param o the o
     * @return the string
     */
    public static String serialize(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param jsonText the json text
     * @param type     the type
     * @return the object
     */
    @SuppressWarnings("rawtypes")
    public static Object deserialize(String jsonText, TypeReference type) {
        if (org.apache.commons.lang3.StringUtils.isBlank(jsonText)) {
            return null;
        }
        try {
            return MAPPER.readValue(jsonText, type);
        } catch (Exception e) {
            LOGGER.warn("反序列化失败:" + e.getMessage());
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param jsonText  the json text
     * @param beanClass the bean class
     * @return the object
     */
    public static <T> T deserialize(String jsonText, Class<T> beanClass) {
        if (org.apache.commons.lang3.StringUtils.isBlank(jsonText)) {
            return null;
        }
        try {
            return MAPPER.readValue(jsonText, beanClass);
        } catch (Exception e) {
            LOGGER.warn("反序列化失败:" + e.getMessage());
        }
        return null;
    }


}

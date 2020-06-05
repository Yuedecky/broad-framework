package com.broad.web.framework.config.fastxml;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * json 序列化字符串值过滤器 比如trim等操作
 *
 * @author broad
 * @date 2019127
 **/
@Slf4j
public class SimpleValueFilter extends SimpleBeanPropertyFilter {


    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        super.serializeAsField(pojo, jgen, provider, writer);
    }
}

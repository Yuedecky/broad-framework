package com.broad.web.framework.config.rest;

import com.broad.web.framework.utils.DateUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author broad
 */
public class StringDateSerializer extends StdSerializer<Date> {


    public static final StringDateSerializer INSTANCE = new StringDateSerializer();


    public StringDateSerializer() {
        super(Date.class);
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DateUtils.formatDate(date, DateUtils.PATTERN_YMD));
    }
}

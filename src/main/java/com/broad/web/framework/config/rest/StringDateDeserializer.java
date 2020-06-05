package com.broad.web.framework.config.rest;

import com.broad.web.framework.utils.DateUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author broad
 */
public class StringDateDeserializer extends StdDeserializer<Date> {

    public static final StringDateDeserializer INSTANCE = new StringDateDeserializer();

    protected StringDateDeserializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext context) throws IOException {
        String value = p.getText().trim();
        return DateUtils.parseDate(value);
    }
}

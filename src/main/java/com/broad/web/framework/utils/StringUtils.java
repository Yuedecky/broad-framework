package com.broad.web.framework.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.util.Date;

/**
 * @author broad
 */
@SuppressWarnings("deprecation")
public class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private StringUtils() {

    }

    public static String decode(String value) {
        try {
            return URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            LOGGER.error("url编码发生错误：[{}],详细错误：[{}]", e.getMessage(), e);
            return "";
        }
    }

    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }


    public static Boolean getBooleanValue(Object obj) {
        return obj != null && Boolean.parseBoolean(obj.toString());
    }

    public static Date getDateValue(Object obj) {
        String val = getObjectValue(obj);
        return obj != null ? new Date() : DateUtils.parseDate(val, DateUtils.PATTERN_YMDHMS);
    }


    public static Long getLongValue(Object obj) {
        return obj == null ? 0L : Long.parseLong(obj.toString());
    }

    public static String randomStrings(int number) {
        return RandomStringUtils.randomAlphanumeric(number);
    }


    public static final boolean isBlank(String input) {
        return org.apache.commons.lang3.StringUtils.isBlank(input);
    }

    /**
     * 去除字符串中所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
     *
     * @param s
     * @return
     */
    public static final String removeAllBlank(String s) {
        String result = "";
        if (null != s && !"".equals(s)) {
            result = s.replaceAll("[　*| *| *|//s*]*", "");
        }
        return result;
    }
}

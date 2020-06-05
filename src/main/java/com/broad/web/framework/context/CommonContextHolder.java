package com.broad.web.framework.context;


import cn.hutool.core.util.StrUtil;
import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.constant.CommonConstant;
import com.broad.web.framework.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author broad
 */
@Slf4j
public class CommonContextHolder {

    private static volatile ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = CONTEXT.get();
        if (map == null) {
            map = new HashMap<>(10);
            CONTEXT.set(map);
        }
        map.put(key, value);
    }

    public static void setInfo(JwtInfo info) {
        set(CommonConstant.JWT_INFO, info);
    }

    public static Object get(String key) {
        Map<String, Object> map = CONTEXT.get();
        if (map == null) {
            map = new HashMap<>(10);
            CONTEXT.set(map);
        }
        return map.get(key);
    }

    public static String getTenant() {
        Object value = get(CommonConstant.JWT_TENANT_NAME);
        return StringUtils.getObjectValue(value);
    }


    public static void setTenant(String val) {
        set(CommonConstant.JWT_TENANT_NAME, val);
    }

    public static String getDatabase(String tenant) {
        Object value = get(CommonConstant.DATABASE_NAME);
        String objectValue = StringUtils.getObjectValue(value);
        return objectValue + StrUtil.UNDERLINE + tenant;
    }

    public static void setDatabase(String val) {
        set(CommonConstant.DATABASE_NAME, val);
    }

    public static void remove() {
        CONTEXT.remove();
    }

    public static void setToken(String token) {
        set(CommonConstant.CONTEXT_KEY_USER_TOKEN, token);
    }

}

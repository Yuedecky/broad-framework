package com.broad.web.framework.utils;

import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.constant.CommonConstant;
import com.broad.web.framework.context.CommonContextHolder;
import com.broad.web.framework.enums.TenantType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author broad
 * 登录工具类
 */
@Slf4j
public class LoginUtils {

    private LoginUtils() {

    }




    public static JwtInfo getInfo() {
        Object value = CommonContextHolder.get(CommonConstant.JWT_INFO);
        return value == null ? null : (JwtInfo) value;
    }

    public static String currentUserName() {
        return getInfo() == null ? "" : getInfo().getLoginName();
    }

    public static String currentNickName() {
        return getInfo() == null ? "" : getInfo().getNickName();
    }

    public static TenantType currentTenantType() {
        return getInfo() == null ? TenantType.UNKNOWN : getInfo().getTenantType();
    }

    public static String currentTenantCode() {
        return getInfo() == null ? "" : getInfo().getTenantCode();
    }

    public static Long currentUserId() {
        return getInfo() == null ? 0L : getInfo().getId();
    }

    public static String platCode() {
        return getInfo() == null ? "" : getInfo().getPlatCode();
    }


    public static String currentUserCode() {
        return getInfo() == null ? "" : getInfo().getUserCode();
    }


    public static String loginIp() {
        return (String) CommonContextHolder.get(CommonConstant.USER_LOGIN_IP);
    }


    public static boolean isPrimaryAccount() {
        return getInfo() == null ? false : getInfo().getPrimaryAccount();
    }


    public static String currentTenantName() {
        return getInfo() == null ? "" : getInfo().getTenantName();
    }
}

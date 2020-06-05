package com.broad.web.framework.utils;

import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.response.WebResponse;

/**
 * web 请求响应 检测类
 */
public class ResultCheckUtils {

    public static boolean resultIsOk(WebResponse response) {
        //首先判断结果是否正确
        return ReturnCode.OK.equals(response.getStatus());
    }

    public static boolean resultHasData(WebResponse response) {
        //首先判断结果是否正确
        if (!ReturnCode.OK.equals(response.getStatus())) {
            return false;
        }
        if (null == response.getData()) {
            return false;
        }
        return true;
    }

}

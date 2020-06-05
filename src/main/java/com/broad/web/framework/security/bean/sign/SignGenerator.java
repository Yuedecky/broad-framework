package com.broad.web.framework.security.bean.sign;

import com.broad.web.framework.utils.MD5Utils;

import java.util.Map;

/**
 * @author broad
 */
public class SignGenerator {

    private SignGenerator() {
    }

    /**
     * 对传递的参数，时间戳，和携带的token进行MD5加密生成sign
     *
     * @param xToken    请求头携带的token
     * @param timeStamp 时间戳
     * @param params    请求参数
     * @return
     */
    public static String generateSign(String xToken, Long timeStamp, Map<String, Object> params) {
        //todo optimise
        return MD5Utils.getMD5Code(xToken + timeStamp);
    }
}

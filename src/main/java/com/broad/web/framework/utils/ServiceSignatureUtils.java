package com.broad.web.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;

/**
 * @author broad
 * @date 20191015
 **/
public class ServiceSignatureUtils {

    private static final String TIME_STAMP_KEY = "timeStamp";
    private static final String SIGN_KEY = "sign";
    /**
     *  超时时效，超过此时间认为签名过期
     */
    private static final Long EXPIRE_TIME = 5 * 60 * 1000L;


    private ServiceSignatureUtils() {

    }


    /**
     * 生成签名
     *
     * @param param     向VIP发送的参数
     * @param secretKey 由VIP提供的私钥，该私钥应存储于properties方便更换
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getSignature(Object param, String secretKey) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map params;
        try {
            String jsonStr = objectMapper.writeValueAsString(param);
            params = objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("生成签名：转换json失败");
        }
        if (params.get(TIME_STAMP_KEY) == null) {
            params.put(TIME_STAMP_KEY, System.currentTimeMillis());
        }
        //对map参数进行排序生成参数
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        //根据参数生成签名
        String sign = DigestUtils.sha256Hex(temp.toString() + secretKey).toUpperCase();
        params.put(SIGN_KEY, sign);
        return params;
    }

    /**
     * 校验签名有效性
     *
     * @param request   core向VIP发起的请求
     * @param secretKey VIP提供的私钥
     * @return
     */
    @SuppressWarnings("unckecked")
    public static boolean checkSignature(HttpServletRequest request, String secretKey) {
        //获取request中的json参数转成map
        Map<String, Object> param;
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            param = objectMapper.readValue(responseStrBuilder.toString(), Map.class);
        } catch (IOException e) {
            return false;
        }
        String sign = (String) param.get(SIGN_KEY);
        Long start = (Long) param.get(TIME_STAMP_KEY);
        long now = System.currentTimeMillis();
        //校验时间有效性
        if (start == null || now - start > EXPIRE_TIME || start - now > 0L) {
            return false;
        }
        //是否携带签名
        if (StringUtils.isBlank(sign)) {
            return false;
        }
        //获取除签名外的参数
        param.remove(SIGN_KEY);
        //校验签名
        Map paramMap = getSignature(param, secretKey);
        String signature = (String) paramMap.get("sign");
        if (sign.equals(signature)) {
            return true;
        }
        return false;
    }


}

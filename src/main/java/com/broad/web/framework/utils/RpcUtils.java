package com.broad.web.framework.utils;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.broad.web.framework.constant.ReturnCode;

public final class RpcUtils {


    private RpcUtils() {

    }

    public static String[] errorBuilder(Exception e) {
        String[] error = ReturnCode.RPC_ERROR;
        error[1] += ":" + e.getMessage();
        return ReturnCode.RPC_ERROR;
    }

    public static Exception errorMsg(String msg) {
        String[] error = ReturnCode.RPC_ERROR;
        error[1] += ":" + msg;
        return new RuntimeException(ReturnCode.RPC_ERROR[1]);
    }


    public static Exception hystrixBadException(String msg) {
        String[] error = ReturnCode.RPC_ERROR;
        error[1] += ":" + msg;
        return new HystrixBadRequestException(ReturnCode.RPC_ERROR[1]);
    }
}

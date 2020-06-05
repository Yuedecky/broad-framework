package com.broad.web.framework.config.feign;

import com.broad.web.framework.exception.BaseException;
import com.broad.web.framework.response.WebResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author broad
 */
@Slf4j
public class SimpleErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        log.warn("进入error decoder,methodKey:{},response:{}", methodKey, response);
        ObjectMapper om = new ObjectMapper();
        WebResponse<?> resEntity = null;
        Exception exception = null;
        try {
            String res = Util.toString(response.body().asReader());
            resEntity = om.readValue(res, WebResponse.class);
            exception = new BaseException(resEntity.getMsg(), response.status() + "");
        } catch (IOException ex) {
            log.error("objectMapper读取value发生错误:{},{}", ex.getMessage(), ex);
        }
        if (resEntity != null && resEntity.getSuccess()) {
            exception = new HystrixBadRequestException(resEntity.getMsg());
        } else {
            log.error("feign远程服务调用发生异常:{}", exception);
        }
        return exception;
    }
}

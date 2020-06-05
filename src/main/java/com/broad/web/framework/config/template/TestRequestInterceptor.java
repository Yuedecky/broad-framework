package com.broad.web.framework.config.template;

import com.broad.web.framework.bean.AuthProperties;
import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author broad
 */
@Component
public class TestRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private AuthUtils authUtils;


    /**
     * 主要是给测试 http 请求添加请求header
     *
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders httpHeaders = request.getHeaders();
        httpHeaders.add(authProperties.getTokenHeader(), authUtils.generateDefault(JwtInfo.defaultInfo()));
        return execution.execute(request, body);
    }
}

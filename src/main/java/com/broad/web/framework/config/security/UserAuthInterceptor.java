package com.broad.web.framework.config.security;

import com.broad.web.framework.utils.AuthUtils;
import com.broad.web.framework.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.annotation.IgnoreUserToken;
import com.broad.web.framework.bean.AuthProperties;
import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.constant.CommonConstant;
import com.broad.web.framework.context.CommonContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auth broad
 */
@Slf4j
@Component
public class UserAuthInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private AuthUtils authUtil;

    public UserAuthInterceptor() {
        log.info("UserAuthInterceptor init...");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!authProperties.isEnable()) {
            return super.preHandle(request, response, handler);
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 配置该注解，说明不进行用户拦截
            IgnoreUserToken annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
            if (annotation != null) {
                //登录
                String ip = IpUtils.getIpAddr(request);
                CommonContextHolder.set(CommonConstant.USER_LOGIN_IP, ip);
                return super.preHandle(request, response, handler);
            }
            String token = request.getHeader(authProperties.getTokenHeader());
            //开始校验token
            JwtInfo info = authUtil.getJwtFromToken(token);
            CommonContextHolder.setInfo(info);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CommonContextHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}

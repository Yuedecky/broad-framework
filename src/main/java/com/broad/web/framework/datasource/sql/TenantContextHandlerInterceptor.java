package com.broad.web.framework.datasource.sql;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.constant.CommonConstant;
import com.broad.web.framework.context.CommonContextHolder;
import com.broad.web.framework.utils.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author broad
 * @date 20200111
 **/
@Slf4j
@AllArgsConstructor
public class TenantContextHandlerInterceptor extends HandlerInterceptorAdapter {

    private String databaseName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.warn("not exec!!! url={}", request.getRequestURL());
            return super.preHandle(request, response, handler);
        }
        CommonContextHolder.setDatabase(this.databaseName);
        CommonContextHolder.setTenant(this.getHeader(request, CommonConstant.JWT_TENANT_NAME));
        return super.preHandle(request, response, handler);
    }

    private String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StrUtil.isEmpty(value)) {
            value = request.getParameter(name);
        }
        if (StrUtil.isEmpty(value)) {
            return StrUtil.EMPTY;
        }
        return StringUtils.decode(value);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CommonContextHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }

}

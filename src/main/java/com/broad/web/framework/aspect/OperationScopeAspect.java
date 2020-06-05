package com.broad.web.framework.aspect;

import com.broad.web.framework.enums.TenantType;
import com.broad.web.framework.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.annotation.OperationLimit;
import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.utils.LoginUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author broad
 */
@Slf4j
@Aspect
@Component
public class OperationScopeAspect {


    public OperationScopeAspect(){
        log.warn("Operation scope aspect starting...");
    }

    @Pointcut("@annotation(com.broad.web.framework.annotation.OperationLimit)")
    public void operationLimit() {
    }

    @Around(value = "operationLimit() && @annotation(limit)")
    public Object limit(ProceedingJoinPoint proceedingJoinPoint, OperationLimit limit) throws Throwable {
        //1.获取返回类型
        TenantType[] scopeEnum = limit.scope();
        TenantType value = LoginUtils.currentTenantType();
        if (value == null) {
            return new WebResponse<>(ReturnCode.NEED_LOGIN);
        }
        if (!Arrays.asList(scopeEnum).contains(value)) {
            return new WebResponse<>(ReturnCode.INVALID_PERMISSION_ACCESS);
        }
        log.warn("operationLimit proceed!!!");
        return proceedingJoinPoint.proceed();
    }

}

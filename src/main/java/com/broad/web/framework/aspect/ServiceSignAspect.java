package com.broad.web.framework.aspect;

import com.broad.web.framework.annotation.RequireSign;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author broad
 * @date 20191015
 **/
@Slf4j
@Aspect
@Component
public class ServiceSignAspect {

    @Pointcut("@annotation(com.broad.web.framework.annotation.RequireSign)")
    public void signValue() {
    }

    @Around("signValue()&&@annotation(sign)")
    public Object validateSign(final ProceedingJoinPoint pjp, RequireSign sign) throws Throwable {
        //校验签名的逻辑 todo
        String signParams = sign.sign();
        log.warn("请求签名：{}", signParams);
        return pjp.proceed();

    }
}

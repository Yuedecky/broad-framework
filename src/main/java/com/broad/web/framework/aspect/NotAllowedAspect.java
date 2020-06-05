package com.broad.web.framework.aspect;

import com.broad.web.framework.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.annotation.NotAllowed;
import com.broad.web.framework.constant.ReturnCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author broad
 */
@Component
@Aspect
@Slf4j
public class NotAllowedAspect {

    @Pointcut("@annotation(com.broad.web.framework.annotation.NotAllowed)")
    public void permits() {
    }

    @Around("permits()&& @annotation(notAllowed))")
    public Object notAllow(final ProceedingJoinPoint point, NotAllowed notAllowed) throws Throwable {
        log.error("非法访问");
        throw new BaseException(ReturnCode.METHOD_NOT_ALLOW);
    }
}

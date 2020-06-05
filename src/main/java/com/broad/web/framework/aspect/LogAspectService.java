package com.broad.web.framework.aspect;

import com.broad.web.framework.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author broad
 */
@Aspect
@Order(5)
@Component
@Slf4j
public class LogAspectService {

    /**
     * 定义切面
     */
    @Pointcut("execution(* net.newfirst.*.*.service..*(..))")
    private void recordServiceLog() {

    }

    @Around("recordServiceLog()")
    public Object aroundLogCalls(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getName();
        //获得方法名称
        String methodName = pjp.getSignature().getName();
        //获得方法参数列表
        Object[] args = pjp.getArgs();
        long threadId = Thread.currentThread().getId();
        log.info("[LogAspectService] start. CLASS-NAME:[{}],METHOD-NAME:[{}],THREAD-ID:[{}],ARGS:[{}]",
                className, methodName, threadId, Arrays.toString(args));
        Object result = pjp.proceed();
        log.info("[LogAspectService] end. CLASS-NAME:[{}],METHOD-NAME:[{}],THREAD-ID:[{}],RESULT:[{}]",
                className, methodName, threadId, JsonUtils.serialize(result));
        return result;
    }

}

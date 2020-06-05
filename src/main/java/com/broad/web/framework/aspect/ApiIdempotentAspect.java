package com.broad.web.framework.aspect;

import com.broad.web.framework.bean.AuthProperties;
import com.broad.web.framework.cache.configuration.RedisAutoConfigure;
import com.broad.web.framework.cache.lock.RedisDistributedLock;
import com.broad.web.framework.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.annotation.ApiIdempotent;
import com.broad.web.framework.constant.ReturnCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 防止重复提交的注解
 * @author broad
 * @date 20200118
 **/
@Aspect
@ConditionalOnClass(value = RedisAutoConfigure.class)
@Slf4j
public class ApiIdempotentAspect {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private RedisDistributedLock distributedLock;





    @Pointcut("@annotation(apiIdempotent)")
    public void pointcut(ApiIdempotent apiIdempotent) {

    }



    @Around("pointcut(apiIdempotent)")
    public Object around(ProceedingJoinPoint pjp, ApiIdempotent apiIdempotent) throws Throwable {
        int lockSeconds = apiIdempotent.lockSeconds();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            String token = servletRequestAttributes.getRequest().getHeader(authProperties.getTokenHeader());
            String path = servletRequestAttributes.getRequest().getContextPath();
            String key = "FRAMEWORK::" + token + "#" + path;
            boolean lock = this.distributedLock.lock(key, 3, lockSeconds * 1000L);
            log.warn("防止重复提交：key=[{}],retryTimes=[{}],lockMills=[{}]", key, 3, lockSeconds * 1000);
            if (lock) {
                log.warn("获取锁成功，key=[{}]，锁定时间=[{}]", key, lockSeconds * 1000);
                Object result;
                try {
                    result = pjp.proceed();
                } finally {
                    this.distributedLock.releaseLock(key);
                    log.warn("释放锁成功，key=[{}]", key);
                }
                return result;
            }
            log.warn("防止重复提交，获取锁失败，key=[{}]", key);
            throw new BaseException(ReturnCode.REQUEST_CURRENCY_OVERLOAD);
        }
        throw new BaseException(ReturnCode.INVALID_PARAMS);
    }

}

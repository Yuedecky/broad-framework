package com.broad.web.framework.aspect;

import com.broad.web.framework.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author broad
 */
@Aspect
@Component
public class LogAspectController {

    private static final Logger logger = LoggerFactory.getLogger(LogAspectController.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * net.newfirst.*.*.facade.impl..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            //记录下请求内容
            logger.info("[LogAspectController]--URL:{},HttpMethod:{},IP:{},CLASS_METHOD:{},ARGS:{}",
                    request.getRequestURL().toString(),
                    request.getMethod(),
                    request.getRemoteAddr(),
                    joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }


    }



    /**
     * 处理完请求返回内容
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        logger.info("[LogAspectController] process end. Response:[{}],Cost time:[{}ms]",
                JsonUtils.serialize(ret), System.currentTimeMillis() - startTime.get());
        startTime.remove();
    }


}

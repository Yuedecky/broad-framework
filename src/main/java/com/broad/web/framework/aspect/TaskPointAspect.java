package com.broad.web.framework.aspect;

import com.broad.web.framework.bean.TaskErrorEvent;
import com.broad.web.framework.dto.TaskErrorDto;
import com.broad.web.framework.utils.AnnotationUtils;
import com.broad.web.framework.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.context.CommonContextHolder;
import com.broad.web.framework.utils.LoginUtils;
import com.broad.web.framework.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;

/**
 * @author broad
 * @date 20200217
 **/
@Component
@Aspect
@Slf4j
public class TaskPointAspect {

    @Pointcut("@annotation(com.broad.web.framework.annotation.TaskPoint)")
    public void taskPoint() {
    }

    @Autowired
    private AuthUtils authUtils;


    public void setContextDefaultToken(String taskName) {
        log.warn("定时任务开始，为请求设置默认token，当前定时任务名称：{}", taskName);
        final JwtInfo info = JwtInfo.defaultInfo();
        if (!StringUtils.isBlank(taskName)) {
            info.setLoginName(taskName);
        }
        String jws = this.authUtils.generateDefault(info);
        CommonContextHolder.setToken(jws);
    }

    public void removeCronDefaultToken() {
        log.warn("开始清除定时任务默认token");
        CommonContextHolder.remove();
    }


    @Autowired
    private ApplicationContext applicationContext;


    @Before("taskPoint()")
    public void doBefore(JoinPoint joinPoint) {
        log.warn("开始处理定时任务：{}", Arrays.toString(joinPoint.getArgs()));
        String taskName = AnnotationUtils.getTaskPointAnnotationValue(joinPoint);
        this.setContextDefaultToken(taskName);
    }

    /**
     * 处理完请求返回内容
     *
     */
    @After(value = "taskPoint()")
    public void doAfter() {
        log.warn("定时任务执行结束");
        this.removeCronDefaultToken();
    }


    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            log.error("定时任务操作异常：{}", e);
        }
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(throwing = "e", pointcut = "taskPoint()")
    public void doAfterThrowable(Throwable e) {
        log.error("定时任务[{}]处理发生错误：{}", LoginUtils.currentUserName(), e);
        tryCatch((aaa) -> {
            final TaskErrorDto errorEvent = new TaskErrorDto();
            errorEvent.setTaskName(LoginUtils.currentUserName());
            // 异常对象
            errorEvent.setThrowable(e);
            errorEvent.setTaskExecuteTime(new Date());
            // 异常信息
            this.applicationContext.publishEvent(new TaskErrorEvent(errorEvent));
        });
    }
}

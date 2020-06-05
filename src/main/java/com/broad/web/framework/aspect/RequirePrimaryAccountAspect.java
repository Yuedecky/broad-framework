package com.broad.web.framework.aspect;

import com.broad.web.framework.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.annotation.RequirePrimaryAccount;
import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.utils.LoginUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author broad
 */
@Slf4j
@Aspect
@Component
public class RequirePrimaryAccountAspect {


    @Around("@annotation(primaryAccount)")
    public Object limit(final ProceedingJoinPoint pjp, RequirePrimaryAccount primaryAccount) throws Throwable {
        //1.获取返回类型
        boolean needPrimary = primaryAccount.needPrimaryAccount();
        boolean isPrimary = LoginUtils.isPrimaryAccount();
        if (needPrimary && !isPrimary) {
            //需要主张号权限
            throw new BaseException(ReturnCode.NEED_PRIMARY_ACCOUNT);
        }
        log.warn("主账号权限success proceed!!!");
        return pjp.proceed();
    }
}

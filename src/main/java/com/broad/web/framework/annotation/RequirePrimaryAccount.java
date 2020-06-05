package com.broad.web.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author broad
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePrimaryAccount {

    /**
     * 需要主账号权限
     * @return
     */
    boolean needPrimaryAccount() default true;
}

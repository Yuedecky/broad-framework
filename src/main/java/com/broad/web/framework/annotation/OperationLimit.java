package com.broad.web.framework.annotation;

import com.broad.web.framework.enums.TenantType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author broad
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLimit {


    /**
     * 操作的平台范围
     *
     * @return
     */
    TenantType[] scope() default {TenantType.PLATFORM};

}

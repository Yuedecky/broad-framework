package com.broad.web.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统操作日志
 * @author broad
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {

    /**
     * 描述
     *
     * @return {String}
     */
    String value();

    /**
     * 记录执行参数
     *
     * @return
     */
    boolean recordRequestParam() default true;

    /**
     * 记录返回参数
     *
     * @return
     */
    boolean recordResponseParam() default true;
}

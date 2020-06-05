package com.broad.web.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author broad
 * 是否忽略用戶token
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD,ElementType.TYPE})
public @interface IgnoreUserToken {

}

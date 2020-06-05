package com.broad.web.framework.cache;

import com.broad.web.framework.enums.CacheType;
import com.broad.web.framework.enums.ServiceCacheActionEnum;

import java.lang.annotation.*;

/**
 * @author broad
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ServiceCache {

    /**
     *
     * @return
     */
    ServiceCacheActionEnum action() default ServiceCacheActionEnum.NOTHING;


    /**
     * 缓存的范围
     * @return
     */
    CacheType scope() default CacheType.DEFAULT_REGION ;
}

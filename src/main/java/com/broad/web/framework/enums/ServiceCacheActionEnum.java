package com.broad.web.framework.enums;

/**
 * 缓存范围枚举
 */
public enum ServiceCacheActionEnum {


    /**
     * 不使用缓存
     */
    NOTHING,

    /**
     * 对新增进行缓存
     */
    ADD,

    /**
     * 只获取缓存
     */
    GET,

    /**
     * 只更新缓存
     */
    UPDATE,

    /**
     * 对缓存进行清理
     */
    EVICT,


    CLEAR,//清楚本缓存实例所有的缓存
}

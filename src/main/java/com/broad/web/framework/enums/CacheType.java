package com.broad.web.framework.enums;


public enum CacheType {
    /**
     * 默认缓存(永不过期，堆占用 100 个对象，堆外占用 10MB，磁盘占用 1GB)
     */
    DEFAULT_REGION,
    /**
     * 字典缓存(永不过期，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    DICT_TRANSLATION,
    /**
     * 字典 INFO 缓存(永不过期，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    DICT_INFO_TRANSLATION,
    /**
     * 地区缓存(永不过期，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    AREA_INFO_TRANSLATION,
    /**
     * 代收代付缓存(永不过期，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    PROPAY_INFO_TRANSLATION,
    /**
     * 社保账户信息(缓存 10 分钟，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    ACCT_INFO,
    /**
     * 用户模块用户信息(缓存 10 分钟，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    USER_INFO,
    /**
     * 用户模块角色信息(缓存 10 分钟，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    ROLE_INFO,
    /**
     * 用户模块公司信息(缓存 10 分钟，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    CORP_INFO,
    /**
     * 用户模块机构信息(缓存 10 分钟，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    ORG_INFO,
    /**
     * 用户角色信息(缓存 10 分钟，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    USER_ROLE_INFO,

    /**
     * 用户数据权限信息(缓存 100 分钟，堆占用 1000 个对象，堆外占用 10MB，磁盘占用 200MB)
     */
    USER_PERMISSION_DATA_INFO;

    /**
     * 过期策略
     */
    /**
     * 堆空间占用数量(不大于 0 则转为默认的 1000)
     */
    /**
     * 堆空间占用数量的单位
     */
    /**
     * 堆外空间占用数量(不大于 0 则不启用)
     */
    /**
     * 堆外空间占用数量的单位
     */
    /**
     * 磁盘空间占用数量(不大于 0 则不启用)
     */
    /**
     * 磁盘空间占用数量的单位
     */
}

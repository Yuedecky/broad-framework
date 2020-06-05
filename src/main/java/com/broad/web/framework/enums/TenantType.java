package com.broad.web.framework.enums;

/**
 * @author broad
 */
public enum TenantType {

    /**
     *
     */
    SUPPLIER("供货商"),
    /**
     * 分销商
     */
    SELLER("分销商"),
    /**
     * 平台
     */
    PLATFORM("平台"),


    XIAOB("小B"),
    /**
     * 未知
     */
    UNKNOWN("未知"),
    ;

    private String value;

    public String getValue() {
        return value;
    }

    TenantType(String value) {
        this.value = value;
    }
}

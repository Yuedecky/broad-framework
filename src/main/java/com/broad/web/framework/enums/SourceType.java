package com.broad.web.framework.enums;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午3:36-2020/5/16
 * @Last modified by:
 */
public enum SourceType implements BaseEnum {

    /**
     * APP="应用"
     */
    APP("应用"),
    /**
     * SERVICE="服务"
     */
    SERVICE("服务"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;

    SourceType(String desc) {
        this.desc = desc;
    }

    public static SourceType match(String val, SourceType def) {
        for (SourceType enm : SourceType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static SourceType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(SourceType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "APP,SERVICE", example = "APP")
    public String getCode() {
        return this.name();
    }


    @Override
    public String getDesc() {
        return desc;
    }
}

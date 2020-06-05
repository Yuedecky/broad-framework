package com.broad.web.framework.sms;

import com.broad.web.framework.enums.BaseEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午3:14-2020/5/16
 * @Last modified by:
 */
public enum ProviderType implements BaseEnum {


    /**
     * TENCENT="0","腾讯云短信",
     */
    ALI("OK", "阿里云短信", "\\$\\{([^\\}]+)\\}"),
    /**
     * 腾讯
     */
    TENCENT("0", "腾讯云短信", "\\{([^\\}]+)\\}"),
    /**
     * 百度
     */
    BAIDU("1000", "百度云短信", "\\$\\{([^\\}]+)\\}"),
    ;


    ProviderType(String val, String desc, String regex) {
        this.val = val;
        this.desc = desc;
        this.regex = regex;
    }

    @ApiModelProperty(value = "描述")
    private String val;

    private String desc;

    private String regex;

    public static ProviderType match(String val, ProviderType def) {
        for (ProviderType enm : ProviderType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static ProviderType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(ProviderType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "ALI,TENCENT,BAIDU", example = "ALI")
    public String getCode() {
        return this.name();
    }

    public SmsSendStatus getTaskStatus(String code) {
        if (this.val.equalsIgnoreCase(code)) {
            return SmsSendStatus.SUCCESS;
        } else {
            return SmsSendStatus.FAIL;
        }
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

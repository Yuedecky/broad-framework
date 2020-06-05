package com.broad.web.framework.sms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午2:21-2020/5/16
 * @Last modified by:
 */
public enum SmsSendStatus {

    /**
     * 等待发送
     */
    WAITING("等待发送"),
    SUCCESS("发送成功"),
    FAIL("发送失败"),
    ;
    @ApiModelProperty(value = "描述")
    private String desc;

    SmsSendStatus(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SmsSendStatus match(String val, SmsSendStatus def) {
        for (SmsSendStatus enm : SmsSendStatus.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }
}

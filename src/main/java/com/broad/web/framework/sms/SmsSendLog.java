package com.broad.web.framework.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午3:42-2020/5/16
 * @Last modified by:
 */
@Builder
@Data
@AllArgsConstructor
public class SmsSendLog {

    private SmsSendStatus sendStatus;

    private Long taskId;

    private String code;

    private String message;

    private String phone;

    private String bizId;

    /**
     * 发送的返回
     */
    private String ext;

    private int fee;


}

package com.broad.web.framework.sms;

import com.broad.web.framework.common.BaseBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午2:15-2020/5/16
 * @Last modified by:
 * 业务参数
 */
@Data
@AllArgsConstructor
@Builder
public class SmsBO {

    private Long taskId;

    /**
     * 接受者手机号
     */
    private String phone;

    /**
     * 发送账号安全认证的Access Key ID
     */
    private String appId;

    /**
     * 发送账号安全认证的Secret Access Key
     */
    private String appSecret;

    /**
     * 发送使用签名
     */
    private String signName;

    /**
     * 模板code
     */
    private String templateCode;

    /**
     * SMS服务域名 百度/其他第三方需要
     */
    private String endPoint;
    /**
     * 短信参数
     * 腾讯是数组
     * 阿里百度 是json
     */
    private String templateParams;

    /**
     * 短信接收者
     */
    private String receiver;

}

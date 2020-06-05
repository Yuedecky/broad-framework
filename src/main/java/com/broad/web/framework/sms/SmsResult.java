package com.broad.web.framework.sms;

import lombok.Data;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午2:19-2020/5/16
 * @Last modified by:
 */
@Data
public class SmsResult {
    /**
     * 阿里：RequestId 请求ID
     * 腾讯：ext：用户的session内容，腾讯server回包中会原样返回
     * 百度：无
     */
    private String ext;

    /**
     * 阿里：发送回执ID,可根据该ID查询具体的发送状态
     * 腾讯：sid 标识本次发送id，标识一次短信下发记录
     * 百度：requestId 短信发送请求唯一流水ID
     */
    private String bizId;

    /**
     * 状态码:
     * 阿里：返回OK代表请求成功,其他错误码详见错误码列表
     * 腾讯：0表示成功(计费依据)，非0表示失败
     * 百度：1000 表示成功
     */
    private String code;
    /**
     * 状态码的描述
     */
    private String message;
    /**
     * 短信计费的条数:腾讯专用
     */
    private int fee;

    private SmsSendStatus sendStatus = SmsSendStatus.WAITING;


    public static SmsResult build(ProviderType type, String code, String bizId, String ext, String message, int fee) {
        SmsResult smsResult = new SmsResult();
        smsResult.setCode(code);
        smsResult.setBizId(bizId);
        smsResult.setExt(ext);
        smsResult.setMessage(message);
        smsResult.setFee(fee);

        SmsSendStatus status = type.getTaskStatus(code);
        smsResult.setSendStatus(status);
        return smsResult;
    }

    public static SmsResult fail(String message) {
        SmsResult smsResult = new SmsResult();
        smsResult.setSendStatus(SmsSendStatus.FAIL);
        smsResult.setMessage(message);
        return smsResult;
    }

    public boolean isSuccess() {
        return SmsSendStatus.SUCCESS == sendStatus;
    }

}

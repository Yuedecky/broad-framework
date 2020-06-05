package com.broad.web.framework.sms;

import com.broad.web.framework.response.WebResponse;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午3:11-2020/5/16
 * @Last modified by:
 */
public interface SmsStrategy {

    /**
     * 发送短信
     *
     * @param task
     * @param smsTemplate
     */
    WebResponse<String> sendSms(SmsTask task, SmsTemplate smsTemplate);

}

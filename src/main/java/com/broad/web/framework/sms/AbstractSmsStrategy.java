package com.broad.web.framework.sms;

import com.broad.web.framework.constant.MqConstant;
import com.broad.web.framework.response.WebResponse;
import com.broad.web.framework.utils.FastJsonConvertUtils;
import com.broad.web.framework.utils.PhoneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.broad.web.framework.enums.MqExchangeQueueEnum.FOR_TEST;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午3:10-2020/5/16
 * @Last modified by:
 */
@Slf4j
@ConditionalOnClass(value = RabbitTemplate.class)
public abstract class AbstractSmsStrategy implements SmsStrategy {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebResponse<String> sendSms(SmsTask task, SmsTemplate template) {
        String appId = template.getAppId();
        String appSecret = template.getAppSecret();
        String endPoint = template.getUrl();
        // 发送使用签名的调用ID
        String signName = template.getSignName();
        //参数json
        String templateParam = task.getTemplateParams();
        String templateCode = template.getTemplateCode();
        log.info("appId={}, appSecret={}, endPoint={},signName={}, templateCode={}", appId, appSecret, endPoint, signName, templateCode);
        log.info("templateParam={}", templateParam);
        try {
            //解析接受者手机号
            Set<String> phoneList = PhoneUtils.getPhone(task.getReceiver());
            List<SmsSendLog> list = phoneList.stream().map((phone) -> {
                //发送
                SmsResult result = send(SmsBO.builder()
                        .taskId(task.getId()).phone(phone).appId(appId).appSecret(appSecret)
                        .signName(signName).templateCode(templateCode).endPoint(endPoint).templateParams(templateParam)
                        .build());
                log.info("phone={}, result={}", phone, result);
                return SmsSendLog.builder()
                        .taskId(task.getId()).phone(phone).sendStatus(result.getSendStatus())
                        .bizId(result.getBizId()).ext(result.getExt())
                        .code(result.getCode()).message(result.getMessage()).fee(result.getFee()).build();
            }).collect(Collectors.toList());
            final CorrelationData correlationData = new CorrelationData(String.valueOf(task.getId()));
            this.rabbitTemplate.convertAndSend(FOR_TEST.getExchangeName(),
                    FOR_TEST.getRoutingKey(), FastJsonConvertUtils.convertObjectToJSONObject(list),
                    correlationData);
        } catch (Exception e) {
            log.warn("短信发送任务发送失败", e);
            return WebResponse.error(String.valueOf(task.getId()));
        }
        return WebResponse.success(String.valueOf(task.getId()));
    }


    /**
     * 子类执行具体的发送任务
     *
     * @param smsDO
     * @return
     */
    protected abstract SmsResult send(SmsBO smsDO);
}

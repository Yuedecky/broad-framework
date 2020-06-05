package com.broad.web.framework.mq;

import com.broad.web.framework.utils.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午11:05-2020/5/12
 * @Last modified by:
 */
public class MessageHelper {

    private MessageHelper() {

    }

    public static Message objToMsg(Object obj) {
        if (null == obj) {
            return null;
        }

        Message message = MessageBuilder.withBody(JsonUtils.serialize(obj).getBytes()).build();
        // 消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);

        return message;
    }

    public static <T> T msgToObj(Message message, Class<T> clazz) {
        if (null == message || null == clazz) {
            return null;
        }

        String str = new String(message.getBody());
        T obj = JsonUtils.deserialize(str, clazz);

        return obj;
    }
}

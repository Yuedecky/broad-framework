package com.broad.web.framework.mq.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Message;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午10:20-2020/5/15
 * @Last modified by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageReturnSource {

    public Message message;
    public int replyCode;
    public String replyText;
    public String exchange;
    public String routingKey;
}

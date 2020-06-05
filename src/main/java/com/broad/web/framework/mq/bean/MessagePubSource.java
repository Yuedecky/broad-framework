package com.broad.web.framework.mq.bean;

import lombok.Data;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午10:12-2020/5/15
 * @Last modified by:
 */
@Data
public class MessagePubSource {

    private CorrelationData correlationData;

    public Boolean ack;

    public String cause;


}

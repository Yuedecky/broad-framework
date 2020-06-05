package com.broad.web.framework.enums;

import org.springframework.amqp.core.ExchangeTypes;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午8:28-2020/5/21
 * @Last modified by:
 */
public enum MqExchangeQueueEnum {

    /**
     *
     */
    FOR_TEST("for.test", "for.*", "for.exchange", ExchangeTypes.TOPIC);

    private final String queueName;

    private final String routingKey;

    private final String exchangeName;

    private final String exchangeType;


    MqExchangeQueueEnum(String queueName, String routingKey, String exchangeName, String exchangeType) {
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.exchangeName = exchangeName;
        this.exchangeType = exchangeType;
    }

    public String getQueueName() {
        return queueName;
    }


    public String getRoutingKey() {
        return routingKey;
    }


    public String getExchangeName() {
        return exchangeName;
    }

    public String getExchangeType() {
        return exchangeType;
    }
}

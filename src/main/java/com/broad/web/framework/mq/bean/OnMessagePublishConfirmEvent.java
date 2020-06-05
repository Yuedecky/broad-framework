package com.broad.web.framework.mq.bean;

import org.springframework.context.ApplicationEvent;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午10:11-2020/5/15
 * @Last modified by:
 */
public class OnMessagePublishConfirmEvent extends ApplicationEvent {

    private MessagePubSource source;


    public OnMessagePublishConfirmEvent(MessagePubSource source) {
        super(source);
    }


}

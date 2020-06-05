package com.broad.web.framework.mq.bean;

import org.springframework.context.ApplicationEvent;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午10:19-2020/5/15
 * @Last modified by:
 */
public class OnMessageReturnEvent extends ApplicationEvent {

    public MessageReturnSource source;


    public OnMessageReturnEvent(MessageReturnSource source) {
        super(source);
    }

}

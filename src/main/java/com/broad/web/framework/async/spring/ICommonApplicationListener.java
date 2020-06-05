package com.broad.web.framework.async.spring;


import java.util.EventListener;

public interface ICommonApplicationListener<E extends CommonApplicationEvent> extends EventListener {

    /**
     * if an event can be supported
     * @param event
     * @return
     */
    boolean supportsEventType(E event);



    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}

package com.broad.web.framework.async.spring;

/**
 * 参考spring 设计事件发布
 * @author broad
 */
public interface ICommonApplicationEventMulticaster {
    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ICommonApplicationListener listener);


    /**
     * remove the listener
     * @param listener
     */
    void removeApplicationListener(ICommonApplicationListener listener);

    /**
     * remover all of the registered listeners
     */
    void removerAllApplicationListeners();


    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(CommonApplicationEvent event);
}

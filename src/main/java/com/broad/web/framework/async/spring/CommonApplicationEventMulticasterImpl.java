package com.broad.web.framework.async.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author broad
 * @date 20200111
 **/
@Component
public class CommonApplicationEventMulticasterImpl implements ICommonApplicationEventMulticaster {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonApplicationEventMulticasterImpl.class);


    public final Set<ICommonApplicationListener> applicationListeners = new LinkedHashSet<>();

    @Override
    public void addApplicationListener(ICommonApplicationListener listener) {
        this.applicationListeners.add(listener);
    }

    @Override
    public void multicastEvent(CommonApplicationEvent event) {

        try {
            for (ICommonApplicationListener applicationListener : applicationListeners) {
                //判断listener是否支持处理该事件，如果支持，则丢给listener处理
                if (applicationListener.supportsEventType(event)) {
                    applicationListener.onApplicationEvent(event);
                }
            }
        } catch (Exception e) {
            LOGGER.error("发布事件出错：[{}]", e);
        }
    }

    @Override
    public void removeApplicationListener(ICommonApplicationListener listener) {
        this.applicationListeners.remove(listener);
    }

    @Override
    public void removerAllApplicationListeners() {
        this.applicationListeners.clear();
    }
}

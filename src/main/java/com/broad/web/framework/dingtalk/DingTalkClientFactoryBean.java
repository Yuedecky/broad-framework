package com.broad.web.framework.dingtalk;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.client.RestTemplate;

/**
 * @author broad
 * @date 20191104
 **/
@Slf4j
public class DingTalkClientFactoryBean implements FactoryBean<DingTalkClient>, InitializingBean, DisposableBean {

    private DingTalkClient dingTalkClient;
    private String webHookUrl;
    private RestTemplate restTemplate;


    @Override
    public void afterPropertiesSet() {
        Assert.notNull(webHookUrl,"Ding ding group web hook url cannot be null");
        Assert.notNull(restTemplate,"Ding ding init rest template cannot be null");
        this.dingTalkClient = new DingTalkClient(webHookUrl,restTemplate);
    }

    @Override
    public void destroy() {
        //do noting
        log.warn("DingTalk client destroying ...");
    }

    @Override
    public Class<?> getObjectType() {
        return DingTalkClient.class;
    }

    @Override
    public DingTalkClient getObject() {
        return this.dingTalkClient;
    }
}

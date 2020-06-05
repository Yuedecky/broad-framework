package com.broad.web.framework.dingtalk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author broad
 * @date 20191103
 **/
@ConfigurationProperties(prefix = "ding.alert")
@Data
public class DingTalkConfigurationProperties {

    /**
     * 消息级别
     */
    private DingTalkMessageLevel messageLevel;

    /**
     * 钉钉 webhook钩子url
     */
    private String webHookUrl;

    /**
     * 服务列表
     */
    private List<DingTalkGroupServiceBean> services;


}

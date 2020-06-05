package com.broad.web.framework.task;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author broad
 * @date 20191226
 **/
@Component
@Slf4j
public class XxlJobConfiguration {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.app-name}")
    private String appName;


    @Value("${xxl.job.executor.port}")
    private Integer port;

    @Value("${xxl.job.access-token}")
    private String accessToken;

    @Value("${xxl.job.executor.log-path}")
    private String logPath;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.warn(">>>>>>>>>>> xxl-job config init... <<<<<<<<<<<<");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(-1);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        return xxlJobSpringExecutor;
    }
}

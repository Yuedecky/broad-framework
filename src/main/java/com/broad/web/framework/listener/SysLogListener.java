package com.broad.web.framework.listener;

import cn.hutool.core.util.StrUtil;
import com.broad.web.framework.bean.SysLogEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.context.CommonContextHolder;
import com.broad.web.framework.dto.OptLogDto;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

/**
 * @author broad
 * @date 20200217
 **/
@AllArgsConstructor
@Data
@Slf4j
public class SysLogListener {


    private Consumer<OptLogDto> consumer;
    private String database;


    @Order
    @Async
    @EventListener(SysLogEvent.class)
    public void logSys(SysLogEvent event) {
        OptLogDto sysLog = (OptLogDto) event.getSource();
        if (sysLog == null || StrUtil.isEmpty(sysLog.getTenantCode())) {
            log.warn("租户编码不存在，忽略操作日志=={}", sysLog.getRequestUri());
            return;
        }
        CommonContextHolder.setDatabase(database);
        CommonContextHolder.setTenant(sysLog.getTenantCode());
        consumer.accept(sysLog);
    }


}

package com.broad.web.framework.listener;

import com.broad.web.framework.bean.TaskErrorEvent;
import com.broad.web.framework.dto.TaskErrorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author broad
 * @date 20200217
 **/
@AllArgsConstructor
@Data
@Slf4j
public class TaskErrorListener {

    @Order
    @Async
    @EventListener(TaskErrorEvent.class)
    public void onTaskError(TaskErrorEvent taskErrorEvent) {
        TaskErrorDto errorDto = (TaskErrorDto) taskErrorEvent.getSource();
        log.error("收到定时任务处理错误信息:{}", errorDto.getThrowable());

    }

}

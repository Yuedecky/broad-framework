package com.broad.web.framework.bean;

import com.broad.web.framework.dto.TaskErrorDto;
import org.springframework.context.ApplicationEvent;


/**
 * 任务执行错误事件
 * @author broad
 * @date 20200217
 **/
public class TaskErrorEvent extends ApplicationEvent {


    public TaskErrorEvent(TaskErrorDto taskErrorDto){
        super(taskErrorDto);
    }



}

package com.broad.web.framework.dto;

import lombok.Data;
import com.broad.web.framework.constant.EventTypeEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author broad
 * @date 20200217
 **/
@Data
public class TaskErrorDto implements Serializable {

    private String taskName;

    private Date taskExecuteTime;

    private Throwable throwable;

    private String type = EventTypeEnum.TASK.name();
}

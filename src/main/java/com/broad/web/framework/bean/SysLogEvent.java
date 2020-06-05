package com.broad.web.framework.bean;

import com.broad.web.framework.dto.OptLogDto;
import org.springframework.context.ApplicationEvent;

/**
 * @author broad
 * @date 20200119
 **/
public class SysLogEvent extends ApplicationEvent {

    /**
     *
     * @param sysLogDto
     */
    public SysLogEvent(OptLogDto sysLogDto){
        super(sysLogDto);
    }
}

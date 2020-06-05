package com.broad.web.framework.constant;

/**
 * 事件类型枚举
 */
public enum EventTypeEnum {


    /**
     * 日志事件
     */
    LOG("日志事件"),
    TASK("任务事件"),
    SERVICE("业务事件"),
    OTHER("其他事件");

    private String desc;

    EventTypeEnum(String eventDesc){
        this.desc = eventDesc;
    }

    public String getDesc() {
        return desc;
    }
}

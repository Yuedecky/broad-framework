package com.broad.web.framework.dingtalk.request;

import lombok.Data;
import com.broad.web.framework.dingtalk.DingTalkMessageLevel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author broad
 * @date 20191104
 **/
@Data
public class DingTalkSendMessageBaseRequest implements Serializable {

    @NotNull(message = "消息类型必须")
    private DingTalkMessageLevel messageLevel;


    @NotNull(message = "负责人必须")
    private String atPrincipal;


    @NotNull(message = "服务名称必须")
    private String serviceName;


    @NotNull(message = "消息内容必须")
    private String contentText;
}

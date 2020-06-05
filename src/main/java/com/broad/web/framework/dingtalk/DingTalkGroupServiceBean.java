package com.broad.web.framework.dingtalk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author broad
 * @date 20191103
 **/
@Data
@ApiModel("服务基本信息")
public class DingTalkGroupServiceBean implements Serializable {


    @ApiModelProperty("服务名称")
    private String serviceName;


    @ApiModelProperty("服务负责人")
    private String servicePrincipal;


    @ApiModelProperty("服务所在的环境")
    private String serviceEnvironment;


    @ApiModelProperty("服务版本")
    private String serviceVersion;

}

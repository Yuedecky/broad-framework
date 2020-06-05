package com.broad.web.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author broad
 */
@Data
public class BaseSearchDto implements Serializable {
    @ApiModelProperty("当前用户")
    private Long userId;

    @ApiModelProperty("平台code")
    private String tenantCode;
}

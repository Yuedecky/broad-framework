package com.broad.web.framework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("基础下拉dto")
public class BasicDto implements Serializable {

    @ApiModelProperty("label")
    private String label;

    @ApiModelProperty("value")
    private String value;
}

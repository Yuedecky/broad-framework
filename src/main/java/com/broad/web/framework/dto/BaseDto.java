package com.broad.web.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author broad
 */
@Data
public class BaseDto implements Serializable {

    @ApiModelProperty("label")
    private String label;

    @ApiModelProperty("value")
    private Long value;
}

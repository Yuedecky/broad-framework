package com.broad.web.framework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author broad
 */
@ApiModel
@Data
public class PageDto {

    @ApiModelProperty(value = "页码", example = "1")
    private int pageNum = 1;

    @ApiModelProperty(value = "一页数据大小", example = "20")
    private int pageSize = 20;
}

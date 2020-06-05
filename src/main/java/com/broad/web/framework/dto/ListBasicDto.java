package com.broad.web.framework.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel("基础列表dto")
@Data
public class ListBasicDto {

    private List<BasicDto> list = new ArrayList<>();

}

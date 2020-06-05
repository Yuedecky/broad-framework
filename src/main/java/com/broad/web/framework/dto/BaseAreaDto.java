package com.broad.web.framework.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author broad
 * @date 20200203
 **/
@Data
public class BaseAreaDto implements Serializable {

    private Long regionId;

    private String regionName;
}

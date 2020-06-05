package com.broad.web.framework.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author broad
 */
@Data
public class ListBaseDto implements Serializable {

    private List<BaseDto> list;
}

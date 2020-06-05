package com.broad.web.framework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author broad
 * @param <T>
 */
@Data
@NoArgsConstructor
public class OutPageDto<T> {
    private List<T> list;

    private long totalCount;

    public OutPageDto(List<T> result, long totalCount) {
        this.list = result;
        this.totalCount = totalCount;
    }
}

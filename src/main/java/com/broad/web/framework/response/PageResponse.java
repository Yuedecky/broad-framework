package com.broad.web.framework.response;

import com.broad.web.framework.dto.OutPageDto;
import lombok.Data;

/**
 * @author broad
 * @param <T>
 */
@Data
public class PageResponse<T> {
    private OutPageDto<T> data;


    public void setData(OutPageDto<T> data) {
        this.data = data;
    }

    public OutPageDto<T> getData() {
        return data;
    }

    public static <T> PageResponse<T> success(OutPageDto<T> data) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setData(data);
        return pageResponse;
    }


}
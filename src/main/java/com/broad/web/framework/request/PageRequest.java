package com.broad.web.framework.request;

import com.broad.web.framework.dto.PageDto;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class PageRequest<T> extends Request<T> {
    private PageDto page = new PageDto();


    public PageRequest(T data) {
        super(data);
    }
}

package com.broad.web.framework.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @author broad
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request<T> {

    @Valid
    private T data;
}

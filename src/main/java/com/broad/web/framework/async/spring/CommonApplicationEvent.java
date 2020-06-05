package com.broad.web.framework.async.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author broad
 * @date 20200111
 **/
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class CommonApplicationEvent<T> {

    private IEventType eventType;

    private T data;
}

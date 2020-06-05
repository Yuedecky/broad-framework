package com.broad.web.framework.tool;

import java.io.Serializable;

/**
 * @author broad
 * @date 20200111
 **/
public interface IdGenerator<T extends Serializable> {
    /**
     * id 生成器
     *
     * @return
     */
    T generate();
}

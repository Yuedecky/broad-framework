package com.broad.web.framework.mybatis.handler;

import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author zuihou
 */
@Alias("fullLike")
public class FullLikeTypeHandler extends BaseLikeTypeHandler {
    public FullLikeTypeHandler() {
        super(true, true);
    }
}

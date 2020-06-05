package com.broad.web.framework.mybatis.handler;


import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author zuihou
 */
@Alias("leftLike")
public class LeftLikeTypeHandler extends BaseLikeTypeHandler {
    public LeftLikeTypeHandler() {
        super(true, false);
    }
}

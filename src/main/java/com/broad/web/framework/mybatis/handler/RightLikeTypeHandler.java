package com.broad.web.framework.mybatis.handler;


import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author zuihou
 */
@Alias("rightLike")
public class RightLikeTypeHandler extends BaseLikeTypeHandler {
    public RightLikeTypeHandler() {
        super(false, true);
    }
}


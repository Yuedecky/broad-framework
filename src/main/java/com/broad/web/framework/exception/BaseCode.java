package com.broad.web.framework.exception;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 上午12:03-2020/5/16
 * @Last modified by:
 */
public interface BaseCode {

    /**
     * 获取错误吗
     *
     * @return
     */
    String getStatus();

    /**
     * 获取信息
     *
     * @return
     */
    String getMsg();
}

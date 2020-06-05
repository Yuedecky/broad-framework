package com.broad.web.framework.file;

import com.broad.web.framework.exception.BaseCode;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 上午12:05-2020/5/16
 * @Last modified by:
 */
public enum FileBaseCode implements BaseCode {


    /**
     *
     */
    UPLOAD_FAIL("502", "文件上传失败"),
    ;

    private String status;

    private String msg;

    FileBaseCode(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getStatus() {
        return status;
    }
}

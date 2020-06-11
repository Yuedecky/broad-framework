package com.broad.web.framework.exception;

import com.broad.web.framework.constant.ReturnCode;
import lombok.Data;

/**
 * @author broad
 */
@Data
public class BaseException extends RuntimeException {

    private String status;

    private String message;

    private String[] errorConstant;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BaseException() {
    }

    public boolean isOk() {
        return this.status.equals(ReturnCode.OK);
    }

    public BaseException(String message, String status) {
        super(message);
        this.message = message;
        this.status = status;
    }


    public BaseException(String status, String format, Object... args) {
        super(String.format(format, args));
        this.status = status;
        this.message = String.format(format, args);
    }

    public BaseException(String message, String status, String[] errorConstant) {
        super(message);
        this.status = status;
        this.message = message;
        this.errorConstant = errorConstant;
    }


    public BaseException(String[] errorConstant) {
        super(errorConstant[1]);
        this.message = errorConstant[1];
        this.status = errorConstant[0];
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
        this.status = ReturnCode.ERROR;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = ReturnCode.ERROR;
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.status = ReturnCode.ERROR;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    public static BaseException wrap(String message, Object... args) {
        return new BaseException(ReturnCode.NO, message, args);
    }


    public static BaseException wrap(String[] returnCode) {
        return new BaseException(returnCode[1], returnCode[0]);
    }

    public static BaseException wrap(BaseCode ex) {
        return new BaseException(ex.getStatus(), ex.getMsg());
    }


}

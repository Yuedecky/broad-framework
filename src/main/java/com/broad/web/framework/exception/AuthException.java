package com.broad.web.framework.exception;

/**
 * @author broad
 */
public class AuthException extends BaseException {
    public AuthException(String[] errorConstant) {
        super(errorConstant);
    }
}

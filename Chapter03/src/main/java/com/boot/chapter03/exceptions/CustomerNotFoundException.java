package com.boot.chapter03.exceptions;

import java.io.Serial;

public class CustomerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public CustomerNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }
    public CustomerNotFoundException(final String message){
        super(message);
        this.errMsgKey = ErrorCode.CUSTOMER_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.CUSTOMER_NOT_FOUND.getErrCode();
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

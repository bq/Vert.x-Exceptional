package com.bq.errorhandler.errors;

import com.bq.errorhandler.dtos.ErrorDto;

/**
 * CustomError is a parent exception that must be extended.
 * All server exception should be a CustomError in order to
 * be handled correctly.
 */
public abstract class CustomError extends RuntimeException {

    /**
     * POJO object that would be returned to end user.
     */
    protected ErrorDto error = new ErrorDto();

    /**
     * @param msg is a short description that explains the error.
     */
    public CustomError(String msg) {
        super(msg);
    }

    /**
     * @return ErrorDto pojo.
     */
    public ErrorDto getError() {
        return error;
    }

    /**
     * @return whether the exception has to be reported or not.
     */
    public boolean isReportable() {
        return false;
    }
}

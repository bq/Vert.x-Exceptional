package com.bq.errorhandler.errors;

import com.bq.errorhandler.dtos.ErrorBag;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * NotFoundError indicates that the required entity couldn't be found.
 */
public class NotFoundError extends CustomError {

    /**
     * ERROR_CODE represents a unique error code
     */
    public final static Integer ERROR_CODE = ErrorsDictionary.ENTITY_NOT_FOUND.getCode();

    /**
     * ERROR_MSG represents a high level error description.
     */
    public final static String ERROR_MSG = "Entity not found.";

    /**
     * STATUS_CODE represents the error code corresponding to the HTTP protocol.
     */
    public final static Integer STATUS_CODE = HttpResponseStatus.NOT_FOUND.code();

    /**
     * @param msg is a short description that explain the error
     */
    public NotFoundError(String msg) {
        super(msg);
        error.setMsg(ERROR_MSG);
        error.setStatus(STATUS_CODE);
        error.setErrorCode(ERROR_CODE);
        error.setCauses(ErrorBag.empty());
    }
}

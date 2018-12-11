package com.bq.errorhandler.errors;

import com.bq.errorhandler.dtos.ErrorBag;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * InvalidInboundEntityException represents all dto validation errors
 */
public class InvalidInboundEntityException extends CustomError {

	/**
	 * ERROR_CODE represents a unique error code
	 */
	public final static Integer ERROR_CODE = ErrorsDictionary.INVALID_INBOUND_ENTITY.getCode();

	/**
	 * ERROR_MSG represents a high level error description.
	 */
	public final static String ERROR_MSG = "Entity not found.";

	/**
	 * STATUS_CODE represents the error code corresponding to the HTTP protocol.
	 */
	public final static Integer STATUS_CODE = HttpResponseStatus.UNPROCESSABLE_ENTITY.code();

	/**
	 * @param msg is a short description that explain the error
	 */
	public InvalidInboundEntityException(String msg) {
		super(msg);
		error.setStatus(STATUS_CODE);
		error.setMsg(ERROR_MSG);
		error.setErrorCode(ERROR_CODE);
		error.setCauses(ErrorBag.empty());
	}

	public InvalidInboundEntityException(ErrorBag errorBag) {
		super("");
		error.setStatus(STATUS_CODE);
		error.setMsg(ERROR_MSG);
		error.setErrorCode(ERROR_CODE);
		error.setCauses(errorBag);
	}

}

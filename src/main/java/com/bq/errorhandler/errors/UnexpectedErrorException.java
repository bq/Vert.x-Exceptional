package com.bq.errorhandler.errors;

import com.bq.errorhandler.dtos.ErrorBag;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * UnexpectedErrorException represents some error that is out of control.
 */
public class UnexpectedErrorException extends CustomError {

	/**
	 * ERROR_CODE represents a unique error code
	 */
	public final static Integer ERROR_CODE = ErrorsDictionary.UNEXPECTED_ERROR.getCode();

	/**
	 * ERROR_MSG represents a high level error description.
	 */
	public final static String ERROR_MSG = "Something unpredictable blow up on your server!.";

	/**
	 * STATUS_CODE represents the error code corresponding to the HTTP protocol.
	 */
	public final static Integer STATUS_CODE = HttpResponseStatus.INTERNAL_SERVER_ERROR.code();

	/**
	 * @param msg is a short description that explain the error
	 */
	public UnexpectedErrorException(String msg) {
		super(msg);
		error.setStatus(STATUS_CODE);
		error.setErrorCode(ERROR_CODE);
		error.setMsg(ERROR_MSG);
		error.setCauses(ErrorBag.empty());
	}

	@Override
	public boolean isReportable() {
		return true;
	}
}

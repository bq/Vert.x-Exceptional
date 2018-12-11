package com.bq.errorhandler.dtos;

import java.util.Optional;

/**
 * POJO that encapsulates the error that will be returned to the end user.
 */
public class ErrorDto {

    /**
     * HTTP status code.
     */
    private int status;

    /**
     * Internal error code that identifies the error.
     */
    private int errorCode;

    /**
     * Short description of the error.
     */
    private String msg;

    /**
     * Causes that provoked the error.
     */
    private Optional<ErrorBag> causes;

    public ErrorDto() {
        causes = Optional.empty();
    }

    public ErrorDto(int status, int errorCode, String msg, ErrorBag causes) {
        this.status = status;
        this.errorCode = errorCode;
        this.msg = msg;
        this.causes = Optional.ofNullable(causes);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ErrorBag getCauses() {
        return causes.orElse(ErrorBag.empty());
    }

    public void setCauses(ErrorBag causes) {
        this.causes = Optional.ofNullable(causes);
    }

}

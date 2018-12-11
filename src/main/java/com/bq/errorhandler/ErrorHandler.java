package com.bq.errorhandler;

import com.bq.errorhandler.errors.CustomError;
import com.bq.errorhandler.errors.UnexpectedErrorException;
import com.bq.errorhandler.reporters.ErrorReporter;
import io.vertx.core.json.Json;
import io.vertx.reactivex.ext.web.RoutingContext;

/**
 * ErrorHandler gives a default behaviour for the controllers to manage
 * common errors and errors. It also gives feedback to the user by
 * responding to their requests with a friendly JSON encoded message
 * which explains why the action couldn't be performed.
 */
public class ErrorHandler {

    private ErrorReporter errorReporter;

    public ErrorHandler(ErrorReporter errorReporter) {
        super();
        this.errorReporter = errorReporter;
    }

    /**
     * Method used to log errors or send them to an external service
     * like Sentry. By default logs the error into the console.
     * @param throwable some exception thrown at runtime.
     */
    public void reportError(Throwable throwable) {
        if (!(throwable instanceof CustomError) || ((CustomError) throwable).isReportable()) {
            errorReporter.capture(throwable);
        }
    }

    /**
     * Responses the end user with a JSON representation of the error and with the HTTP status code that fits it.
     * @param routingContext the context in which the router is executing.
     * @param throwable some exception thrown at runtime.
     */
    public void renderError(RoutingContext routingContext, Throwable throwable) {
        CustomError customError = (throwable instanceof CustomError)
                ? (CustomError) throwable
                : new UnexpectedErrorException(throwable.getMessage());

        routingContext.response()
                .setStatusCode(customError.getError().getStatus())
                .putHeader("content-type", "application/json")
                .end(Json.encode(customError.getError()));
    }

    /**
     * Handles the error/exception reporting and rendering it by default.
     * @param routingContext the context in which the router is executing.
     * @param throwable some exception thrown at runtime.
     */
    public void handleError(RoutingContext routingContext, Throwable throwable) {
        reportError(throwable);
        renderError(routingContext, throwable);
    }

}

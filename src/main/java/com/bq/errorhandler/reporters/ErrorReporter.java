package com.bq.errorhandler.reporters;

public interface ErrorReporter {

    /**
     * Captures the error/exception and sends it to the error tracking software.
     * @param throwable
     */
    void capture(Throwable throwable);

    /**
     * Attaches a message to the error/exception event to give it additional information.
     * @param message
     * @return a reference to this so the API can be used fluently.
     */
    ErrorReporter withMessage(String message);

    /**
     * Attaches a tag to the error/exception event so that it can be filtered in the error tracking software.
     * @param key name of the tag.
     * @param value value of the tag
     * @return a reference to this so the API can be used fluently.
     */
    ErrorReporter withTag(String key, String value);

}

package com.bq.errorhandler.dtos;

import java.util.HashMap;

/**
 * Wrapper class for HashMap intended for collecting
 * several errors instead of a single one.
 */
public class ErrorBag extends HashMap<String, String> {

    /**
     * Creates and returns an empty ErrorBag.
     * @return the empty ErrorBag instance.
     */
    public static ErrorBag empty() {
        return new ErrorBag();
    }

    @Override
    public String toString() {
        String message = "ErrorBag{";

        for (Entry<String, String> error : entrySet()) {
            message += String.format("\"%s\": \"%s\"\n", error.getKey(), error.getValue());
        }

        return message + "}";
    }

}
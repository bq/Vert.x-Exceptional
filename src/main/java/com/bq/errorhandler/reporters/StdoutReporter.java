package com.bq.errorhandler.reporters;

import java.util.HashMap;
import java.util.Map;

/**
 * StdoutReporter is an error reporter that captures the errors and logs them into the standard output.
 */
public class StdoutReporter implements ErrorReporter {

    private String message;
    private Map<String, String> tags;
    private Throwable error;

    StdoutReporter() {
        message = "";
        tags = new HashMap<>();
    }

    @Override
    public void capture(Throwable throwable) {
        this.error = throwable;

        System.err.println(this);
    }

    @Override
    public ErrorReporter withMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public ErrorReporter withTag(String key, String value) {
        tags.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        String tagsOutput = "";

        for (Map.Entry<String, String> tag : tags.entrySet()) {
            tagsOutput += String.format("{\"%s\":\"%s\"},", tag.getKey(), tag.getValue());
        }

        return String.format("[ERROR] {\"message\": \"%s\",\"tags\": [%s],\"error\":\"%s\"}", message, tagsOutput, error);
    }
}

package com.bq.errorhandler.reporters;

import io.sentry.Sentry;
import io.sentry.event.EventBuilder;

/**
 * SentryReporter is an error reporter that captures and sends error logs to Sentry error tracking software.
 */
public class SentryReporter implements ErrorReporter {

    private EventBuilder eventBuilder;

    SentryReporter() {}

    /**
     * Creates (if necessary) and returns the EventBuilder instance.
     * @return the EventBuilder instance.
     */
    private EventBuilder getEventBuilder() {
        if (eventBuilder == null) {
            eventBuilder = new EventBuilder();
        }

        return eventBuilder;
    }

    @Override
    public ErrorReporter withMessage(String message) {
        getEventBuilder().withMessage(message);
        return this;
    }

    @Override
    public ErrorReporter withTag(String key, String value) {
        getEventBuilder().withTag(key, value);
        return this;
    }

    @Override
    public void capture(Throwable throwable) {
        Sentry.capture(throwable);
    }
}

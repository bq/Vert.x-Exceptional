package com.bq.errorhandler.reporters;

public enum ReporterFactory {

    INSTANCE;

    private final SentryReporter sentryReporter;
    private final StdoutReporter stdoutReporter;

    ReporterFactory() {
        sentryReporter = new SentryReporter();
        stdoutReporter = new StdoutReporter();
    }

    public ErrorReporter newReporter(ReporterType reporterType) {
        ErrorReporter errorReporter;

        switch (reporterType) {
            case SENTRY:
                errorReporter = sentryReporter;
                break;

            case STDOUT:
            default:
                errorReporter = stdoutReporter;
                break;
        }

        return errorReporter;
    }

}

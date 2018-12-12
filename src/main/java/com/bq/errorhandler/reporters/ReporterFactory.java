package com.bq.errorhandler.reporters;

/**
 * Singleton and thread safe factory for obtaining an instance of the desired error reporter.
 */
public enum ReporterFactory {

    /**
     * The unique instance.
     */
    INSTANCE;

    private final SentryReporter sentryReporter;
    private final StdoutReporter stdoutReporter;

    ReporterFactory() {
        sentryReporter = new SentryReporter();
        stdoutReporter = new StdoutReporter();
    }

    /**
     * Returns the instance of the error reporter that corresponds with the given type.
     * @param reporterType type of error reporter.
     * @return the instance of the given error reporter type.
     */
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

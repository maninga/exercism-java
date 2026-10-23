public class LogLine {
    private final String level;
    private final String message;

    public LogLine(String logLine) {
        String[] logLineParts = logLine.split(":", 2);
        this.level = logLineParts[0].replaceAll("[^A-Z]", "");
        this.message = logLineParts[1].trim();
    }

    public String getLogMessage() {
        return this.message;
    }

    public LogLevel getLogLevel() {
        switch (this.level) {
            case "TRC":
                return LogLevel.TRACE;
            case "DBG":
                return LogLevel.DEBUG;
            case "INF":
                return LogLevel.INFO;
            case "WRN":
                return LogLevel.WARNING;
            case "ERR":
                return LogLevel.ERROR;
            case "FTL":
                return LogLevel.FATAL;
            default:
                return LogLevel.UNKNOWN;
        }
    }

    public String getOutputForShortLog() {
        return String.format("%d:%s", this.getLogLevel().getCode(), this.getLogMessage());
    }
}

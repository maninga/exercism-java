public class LogLevels {
    
    public static String message(String logLine) {
        return (logLine.split(":", 2))[1].trim();
    }

    public static String logLevel(String logLine) {
        return (logLine.split(":", 2))[0].replaceAll("[^a-zA-Z]","").toLowerCase();
        
    }

    public static String reformat(String logLine) {
        return LogLevels.message(logLine) + " (" + LogLevels.logLevel(logLine) + ")";
    }
}

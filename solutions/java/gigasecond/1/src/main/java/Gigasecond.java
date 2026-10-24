import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {
    private final int gigasecond = 1_000_000_000;
    private final LocalDateTime startMoment;

    public Gigasecond(LocalDate moment) {
        startMoment = moment.atStartOfDay();
    }

    public Gigasecond(LocalDateTime moment) {
        startMoment = moment;
    }

    public LocalDateTime getDateTime() {
        return startMoment.plusSeconds(gigasecond);
    }
}

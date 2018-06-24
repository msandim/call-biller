package callbiller;

import java.time.Duration;
import java.time.LocalTime;

import callbiller.exceptions.InvalidCallException;
import callbiller.exceptions.InvalidCallTimeStampException; 

public class CallRecord {
    private String origin, destination;
    private long durationSeconds;

    public CallRecord(String origin, String destination, long durationSeconds) {
        this.origin = origin;
        this.destination = destination;
        this.durationSeconds = durationSeconds;
    }

    public static CallRecord parse(String string) throws InvalidCallException, InvalidCallTimeStampException {
        String[] fields = string.split(";");
        
        if (fields.length != 4)
            throw new InvalidCallException(string);

        String origin = fields[2];
        String destination = fields[3];
        String beginningCall = fields[0];
        String endCall = fields[1];

        long durationSeconds = parseCallDuration(beginningCall, endCall);

        return new CallRecord(origin, destination, durationSeconds);
    }

    private static long parseCallDuration(String beginning, String end) {
        LocalTime beginningTime = LocalTime.parse(beginning);
        LocalTime endTime = LocalTime.parse(end);

        Duration duration = Duration.between(beginningTime, endTime);

        // Avoid the case when the day changes inbetween the call (e.g. 23:59:00 to 00:01:00 should be 2 min):
        if (duration.isNegative())
            duration = duration.plusDays(1);

        return duration.toMinutes();
    }
}
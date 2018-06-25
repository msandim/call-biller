package callbiller;

import java.time.Duration;
import java.time.LocalTime;

import callbiller.exceptions.InvalidCallException; 

public class CallRecord {
    private String origin, destination;
    private long durationSeconds;
    private long costCents;

    public CallRecord(String origin, String destination, long durationSeconds) {
        this.origin = origin;
        this.destination = destination;
        this.durationSeconds = durationSeconds;

        // Calculate cost of the call:
        calculateCost();
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public long getDurationSeconds() {
        return this.durationSeconds;
    }

    public long getCostCents() {
        return this.costCents;
    }

    public static CallRecord parse(String string) throws InvalidCallException {
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

        return duration.getSeconds();
    }

    private void calculateCost() {
        long numberOfMinutes = durationSeconds / 60;

        // A call that took 1min and 26s needs to be billed as 2min:
        if (durationSeconds % 60 != 0)
            numberOfMinutes++;
        
        if (numberOfMinutes <= 5)
            this.costCents = numberOfMinutes * 5;
        else {
            long numberOfMinutesAfter5 = numberOfMinutes - 5;
            this.costCents = (5 * 5) + (numberOfMinutesAfter5 * 2);
        }
    }
}
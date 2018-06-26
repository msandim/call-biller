package callbiller;

import java.time.Duration;
import java.time.LocalTime;

import callbiller.exceptions.InvalidCallException;

/**
 * Class that represents a call record from two numbers (origin number to
 * destination number). A CallRecord has also a duration in seconds and a
 * respective cost in cents.
 */
public class CallRecord {
    private String origin, destination;
    private long durationSeconds;
    private long costCents;

    /**
     * Constructor for a CallRecord. It also calculats the cost of the call, based
     * on the duration in seconds.
     * 
     * @param origin          Origin number of the call
     * @param destination     Destination number of the call
     * @param durationSeconds Duration in seconds of the call
     */
    public CallRecord(String origin, String destination, long durationSeconds) {
        this.origin = origin;
        this.destination = destination;
        this.durationSeconds = durationSeconds;

        // Calculate cost of the call:
        calculateCost();
    }

    /**
     * Get method for the origin number of the call.
     */
    public String getOrigin() {
        return this.origin;
    }

    /**
     * Get method for the destination number of the call.
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Get method for the duration of the call.
     */
    public long getDurationSeconds() {
        return this.durationSeconds;
    }

    /**
     * Get method for the cost of the call.
     */
    public long getCost() {
        return this.costCents;
    }

    /**
     * Method that parses a string the format
     * "beginning_of_the_call;end_of_the_call;origin_number;destination_number"
     * (e.g. 17:44:04;17:49:30;+351914374373;+351963433432) into a instance of a
     * CallRecord.
     * 
     * @param string String in the specified format to parse a CallRecord
     */
    public static CallRecord parse(String string) throws InvalidCallException {

        String origin, destination;
        long durationSeconds;

        try {
            String[] fields = string.split(";");

            if (fields.length != 4)
                throw new InvalidCallException(string);

            origin = fields[2];
            destination = fields[3];
            String beginningCall = fields[0];
            String endCall = fields[1];

            durationSeconds = parseCallDuration(beginningCall, endCall);
        } catch (Exception e) { // case when other exceptions other than InvalidCallException are called:
            throw new InvalidCallException(string);
        }

        return new CallRecord(origin, destination, durationSeconds);
    }

    private static long parseCallDuration(String beginning, String end) {
        LocalTime beginningTime = LocalTime.parse(beginning);
        LocalTime endTime = LocalTime.parse(end);

        Duration duration = Duration.between(beginningTime, endTime);

        // Avoid the case when the day changes inbetween the call (e.g. 23:59:00 to
        // 00:01:00 should be 2 min):
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
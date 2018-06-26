package callbiller;

/**
 * Class that represents a bill of a specific number. This bill includes
 * information regarding the number, the total duration of the calls made by
 * this number (in seconds) and the total cost of those calls (in cents).
 */
public class NumberBill {
    private String phoneNumber;
    private long cost;
    private long duration;

    /**
     * Constructor for NumberBill, with the cost and duration initialized with 0.
     * 
     * @param phoneNumber Phone number of the bill
     */
    public NumberBill(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.cost = 0;
        this.duration = 0;
    }

    /**
     * Constructor for NumberBill.
     * 
     * @param phoneNumber Phone number of the bill
     * @param cost        Cost of the bill (in cents)
     * @param duration    Duration of the calls in this bill (in seconds)
     */
    public NumberBill(String phoneNumber, long cost, long duration) {
        this.phoneNumber = phoneNumber;
        this.cost = cost;
        this.duration = duration;
    }

    /**
     * Set method for the phone number.
     */
    public String getNumber() {
        return this.phoneNumber;
    }

    /**
     * Set method for the cost of the bill (in cents).
     */
    public long getCost() {
        return this.cost;
    }

    /**
     * Set method for the duration of calls of this bill (in seconds):
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     * Method that updates a the bill with a call made. This method updates the
     * total cost and duration of the calls in the bill.
     */
    public void addCallData(CallRecord call) {
        this.cost += call.getCost();
        this.duration += call.getDurationSeconds();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        NumberBill other = (NumberBill) obj;
        return this.phoneNumber.equals(other.phoneNumber);
    }
}
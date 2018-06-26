package callbiller;

public class NumberBill {
    private String phoneNumber;
    private long cost;
    private long duration;

    public NumberBill(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.cost = 0;
        this.duration = 0;
    }

    public NumberBill(String phoneNumber, long cost, long duration) {
        this.phoneNumber = phoneNumber;
        this.cost = cost;
        this.duration = duration;
    }

    public String getNumber() {
        return this.phoneNumber;
    }

    public long getCost() {
        return this.cost;
    }

    public long getDuration() {
        return this.duration;
    }

	public void addCallData(CallRecord call) {
        this.cost += call.getCost();
        this.duration += call.getDurationSeconds();
    }
    
    @Override
    public boolean equals(Object obj) 
    {
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
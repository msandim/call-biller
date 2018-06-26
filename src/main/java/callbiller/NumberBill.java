package callbiller;

public class NumberBill {
    private String phoneNumber;
    private long cost;

    public NumberBill(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.cost = 0;
    }

    public NumberBill(String phoneNumber, long cost) {
        this.phoneNumber = phoneNumber;
        this.cost = cost;
    }

    public String getNumber() {
        return this.phoneNumber;
    }

    public long getCost() {
        return this.cost;
    }

	public void addCallCost(long callCost) {
		this.cost += callCost;
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
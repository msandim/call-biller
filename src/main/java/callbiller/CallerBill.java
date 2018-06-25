package callbiller;

public class CallerBill {
    private String phoneNumber;
    private long billCents;

    public CallerBill(String phoneNumber, long billCents) {
        this.phoneNumber = phoneNumber;
        this.billCents = billCents;
    }

    public long getBillCents() {
        return this.billCents;
    }
}
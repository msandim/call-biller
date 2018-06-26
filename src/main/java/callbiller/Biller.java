package callbiller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Biller {

    private Map<String, NumberBill> numberBills = new HashMap<>();
    private List<NumberBill> highestNumberBills = new ArrayList<>();

    public void addCall(CallRecord call) {
        String originNumber = call.getOrigin();

        // If the call's origin is already in the bills, update the bill:
        if (numberBills.containsKey(originNumber))
            numberBills.get(originNumber).addCallData(call);
        else // Otherwise add it:
            numberBills.put(originNumber, new NumberBill(originNumber, call.getCost(), call.getDurationSeconds()));

        NumberBill originNumberBill = numberBills.get(originNumber);

        if (highestNumberBills.isEmpty())
            highestNumberBills.add(originNumberBill);
        // if this number is the one with the highest call duration, delete all bills on highestUserBills and add this one:
        else if (originNumberBill.getDuration() > highestNumberBills.get(0).getDuration()) {
            highestNumberBills.clear();
            highestNumberBills.add(originNumberBill);
        } // In order to cover the case the highest call duration is shared with several numbers:
        else if (originNumberBill.getDuration() == highestNumberBills.get(0).getDuration())
            highestNumberBills.add(originNumberBill);
    }

    public BigDecimal getTotalCost() {
        Long totalCost = 0L;

        // Sum all the costs from each number:
        for (Map.Entry<String, NumberBill> bill : numberBills.entrySet())
            totalCost += bill.getValue().getCost();
        
        // Remove the cost for the numbers with the highest bills (numbers with the longest total call time):
        for (NumberBill bill: highestNumberBills)
            totalCost -= bill.getCost();

        BigDecimal totalCostDecimal = new BigDecimal(totalCost).movePointLeft(2);
        return totalCostDecimal;
    }

    public Map<String, NumberBill> getNumberBills() {
        return this.numberBills;
    }

    public List<NumberBill> getHighestNumberBills() {
        return this.highestNumberBills;
    }
}
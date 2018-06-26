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
            numberBills.get(originNumber).addCallCost(call.getCost());
        else // Otherwise add it:
            numberBills.put(originNumber, new NumberBill(originNumber, call.getCost()));

        NumberBill originNumberBill = numberBills.get(originNumber);

        if (highestNumberBills.isEmpty())
            highestNumberBills.add(originNumberBill);
        // if this number is the one with the highest bill, delete all bills on highestUserBills and add this one:
        else if (originNumberBill.getCost() > highestNumberBills.get(0).getCost()) {
            highestNumberBills.clear();
            highestNumberBills.add(originNumberBill);
        } // In order to cover the case the highest bill is shared with several numbers:
        else if (originNumberBill.getCost() == highestNumberBills.get(0).getCost())
            highestNumberBills.add(originNumberBill);
    }

    public BigDecimal getTotalCost() {
        Long totalCost = 0L;

        for (Map.Entry<String, NumberBill> bill : numberBills.entrySet())
            totalCost += bill.getValue().getCost();
        
        // Remove the cost for the numbers with the highest bills:
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
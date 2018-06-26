package callbiller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class BillerTest {

    @Test
    void testBillsOneHighestBill() {

        Biller biller = new Biller();

        try {
            biller.addCall(CallRecord.parse("09:11:30;09:15:22;+351914374373;+351215355312"));
            biller.addCall(CallRecord.parse("15:20:04;15:23:49;+351217538222;+351214434422"));
            biller.addCall(CallRecord.parse("16:43:02;16:50:20;+351217235554;+351329932233"));
            biller.addCall(CallRecord.parse("17:44:04;17:49:30;+351914374373;+351963433432"));
        } catch (Exception e) {
            fail("Exception was thrown.");
        }

        assertEquals(new BigDecimal("0.51"), biller.getTotalCost(), "Invalid call cost value.");

        Map<String, NumberBill> bills = biller.getNumberBills();
        assertEquals(3, bills.size(), "Invalid size of numbers in the system.");

        Set<String> numbers = bills.keySet();

        if (!numbers.contains("+351914374373") || !numbers.contains("+351217538222")
                || !numbers.contains("+351217235554"))
            fail("Not call numbers were in the system.");

        List<NumberBill> highestBills = biller.getHighestNumberBills();
        assertEquals(1, highestBills.size(), "Invalid size of highest bill numbers in the system.");

        if (!highestBills.contains(new NumberBill("+351914374373")))
            fail("Highest bill number is incorrect.");
    }

    @Test
    void testBillsTwoHighestBills() {

        Biller biller = new Biller();

        try {
            biller.addCall(CallRecord.parse("09:11:30;09:15:22;+351914374373;+351215355312"));
            biller.addCall(CallRecord.parse("15:20:04;15:23:49;+351217538222;+351214434422"));
            biller.addCall(CallRecord.parse("16:43:02;16:50:20;+351217235554;+351329932233"));
            biller.addCall(CallRecord.parse("17:44:04;17:49:30;+351914374373;+351963433432"));
            biller.addCall(CallRecord.parse("17:44:04;17:49:30;+351217538222;+351963433432"));
        } catch (Exception e) {
            fail("Exception was thrown.");
        }

        assertEquals(new BigDecimal("0.31"), biller.getTotalCost(), "Invalid call cost value.");

        Map<String, NumberBill> bills = biller.getNumberBills();
        assertEquals(3, bills.size(), "Invalid size of numbers in the system.");

        Set<String> numbers = bills.keySet();

        if (!numbers.contains("+351914374373") || !numbers.contains("+351217538222")
                || !numbers.contains("+351217235554"))
            fail("Not call numbers were in the system.");

        List<NumberBill> highestBills = biller.getHighestNumberBills();
        assertEquals(2, highestBills.size(), "Invalid size of highest bill numbers in the system.");

        if (!highestBills.contains(new NumberBill("+351914374373"))
                || !highestBills.contains(new NumberBill("+351217538222")))
            fail("Highest bill numbers are incorrect.");
    }
}
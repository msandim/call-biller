package callbiller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class CallRecordTest {

    @Test
    void testInvalidInput1() {
        try {
            CallRecord.parse("09:10:30;09:10:32;+351914374373");
            fail("Invalid input was accepted");
		} catch (Exception e) { }
    }

    @Test
    void testInvalidInput2() {
        try {
            CallRecord.parse("09:10:30;09:120:32;+351914374373;+351215355312");
            fail("Invalid input was accepted");
		} catch (Exception e) { }
    }

    @Test
    void testInvalidInput3() {
        try {
            CallRecord.parse("09:1a:30;09:10:32;+351914374373;+351215355312");
            fail("Invalid input was accepted");
		} catch (Exception e) { }
    }

    @Test
    void testNumberFields() {
        try {
            CallRecord record = CallRecord.parse("09:10:30;09:10:32;+351914374373;+351215355312");
            assertEquals("+351914374373", record.getOrigin(), "Invalid origin number.");
            assertEquals("+351215355312", record.getDestination(), "Invalid destination number.");
            assertEquals(2L, record.getDurationSeconds(), "Invalid duration.");
		} catch (Exception e) {
            fail("Exception was thrown.");
		}
    }

    @Test
    void testCallCost() {
        try {
            CallRecord record = null;
            record = CallRecord.parse("09:10:30;09:11:29;+351914374373;+351215355312");
            assertEquals(5L, record.getCost(), "Invalid call cost.");

            record = CallRecord.parse("09:10:30;09:11:31;+351914374373;+351215355312");
            assertEquals(10L, record.getCost(), "Invalid call cost.");

            record = CallRecord.parse("23:59:00;00:02:00;+351914374373;+351215355312");
            assertEquals(15L, record.getCost(), "Invalid call cost.");

            record = CallRecord.parse("23:59:00;00:02:00;+351914374373;+351215355312");
            assertEquals(15L, record.getCost(), "Invalid call cost at change of day.");

            record = CallRecord.parse("09:10:00;09:15:00;+351914374373;+351215355312");
            assertEquals(25, record.getCost(), "Invalid call cost.");

            record = CallRecord.parse("09:10:00;09:15:01;+351914374373;+351215355312");
            assertEquals(27, record.getCost(), "Invalid call cost.");

            record = CallRecord.parse("09:10:00;09:16:00;+351914374373;+351215355312");
            assertEquals(27, record.getCost(), "Invalid call cost.");

            record = CallRecord.parse("09:10:00;09:17:00;+351914374373;+351215355312");
            assertEquals(29, record.getCost(), "Invalid call cost.");

		} catch (Exception e) {
            fail("Exception was thrown.");
		}
    }
}
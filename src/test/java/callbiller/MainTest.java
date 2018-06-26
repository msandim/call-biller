package callbiller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * Class for tests for the Main class.
 */
public class MainTest {

    /**
     * Test for an invalid path for the file containing the call records.
     */
    @Test
    void testInvalidPath() {
        BigDecimal cost = Main.calculateCallsCost("X");

        assertEquals(new BigDecimal("-1"), cost, "Invalid error return value.");
    }

    /**
     * Test for a valid path for the file containing the call records.
     */
    @Test
    void testValidPath() {
        BigDecimal cost = Main.calculateCallsCost("src/test/resources/validCalls1.txt");

        assertEquals(new BigDecimal("0.51"), cost, "Invalid call cost value.");
    }
}
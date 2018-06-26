package callbiller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void testInvalidPath() {
        BigDecimal cost = Main.calculateCallsCost("X");

        assertEquals(new BigDecimal("-1"), cost, "Invalid error return value.");
    }

    @Test
    void testValidPath() {
        BigDecimal cost = Main.calculateCallsCost("src/test/resources/validCalls1.txt");

        assertEquals(new BigDecimal("0.51"), cost, "Invalid call cost value.");
    }
}
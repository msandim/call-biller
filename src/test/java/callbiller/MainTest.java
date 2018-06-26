package callbiller;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void testInvalidPath() {
        BigDecimal cost = Main.calculateCallsCost("X");
        BigDecimal errorReturn = new BigDecimal(-1);

        if (cost.compareTo(errorReturn) != 0)
            fail("Invalid error return value!");
    }

    @Test
    void testValidPath() {
        BigDecimal cost = Main.calculateCallsCost("src/test/resources/validCalls1.txt");
        BigDecimal errorReturn = new BigDecimal(-1);

        if (cost.compareTo(errorReturn) == 0)
            fail("Invalid valid return value!"); 
    }
}
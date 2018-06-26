package callbiller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Invalid number of arguments!");
            System.exit(-1);
        }

        String filename = args[0];
        BigDecimal cost = calculateCallsCost(filename);

        System.out.println(cost);
    }

    public static BigDecimal calculateCallsCost(String filename) {

        Biller biller = new Biller();

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {

			stream.forEach(line -> {
                try {
                    CallRecord record = CallRecord.parse(line); // parse the line as a call record
                    biller.addCall(record); // add call record to the biller:
				} catch (Exception e) {
					System.err.println("Warning: the following line is an invalid call record: " + line);
                }
            });

		} catch (IOException e) {
            System.err.println("Error: the file \"" + filename + "\" could not be found.");
            return new BigDecimal(-1);
        }

        return biller.getTotalCost();
    }
}
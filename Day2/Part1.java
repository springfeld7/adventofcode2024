import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class Part1 {

    public static void main(final String... args) throws IOException {

        int numOfSafeReports = 0;
        boolean isIncreasing;
        boolean isSafe;

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int[] report = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                isSafe = true;
                isIncreasing = report[0] < report[1];

                for (int i = 1; i < report.length; i++) {
                    int diff = Math.abs(report[i - 1] - report[i]);

                    // Check if difference is invalid or plateau occurs
                    if (diff < 1 || diff > 3 || report[i - 1] == report[i]) {
                        isSafe = false;
                        break;
                    }

                    // If trend switches, it's invalid
                    if (report[i - 1] < report[i] != isIncreasing) {
                        isSafe = false;
                        break;
                    }

                    // Update trend
                    isIncreasing = report[i - 1] < report[i];
                }

                if (isSafe) numOfSafeReports++;
            }
        }
        System.out.println(numOfSafeReports);
    }
}

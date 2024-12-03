import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {

        List<List<Integer>> reports = new ArrayList<>();
        int numOfSafeReports = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                reports.add(Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .toList());
            }
        }

        // Evaluate each report for safety
        for (List<Integer> report : reports) {
            if (isSafe(report) || canBeMadeSafe(report)) {
                numOfSafeReports++;
            }
        }

        System.out.println(numOfSafeReports);
    }

    private static boolean isSafe(List<Integer> report) {

        boolean isIncreasing = report.get(1) > report.get(0);

        for (int i = 1; i < report.size(); i++) {
            int diff = Math.abs(report.get(i) - report.get(i - 1));
            if (diff < 1 || diff > 3 || (report.get(i) > report.get(i - 1)) != isIncreasing) {
                return false;
            }
        }
        return true;
    }

    private static boolean canBeMadeSafe(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafe(modifiedReport)) {
                return true;
            }
        }
        return false;
    }
}

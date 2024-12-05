import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class Part1 {

    public static void main(String[] args) throws IOException {

        List<List<Integer>> rules = readFile("rules.txt", "\\|");
        List<List<Integer>> updates = readFile("updates.txt", ",");

        int sum = calculateSumOfSafeUpdates(rules, updates);

        System.out.println(sum);
    }

    private static int calculateSumOfSafeUpdates(List<List<Integer>> rules, List<List<Integer>> updates) {

        int sum = 0;

        for (List<Integer> update : updates) {

            boolean isSafe = rules.stream().noneMatch(rule ->
                    // For each rule, check if both numbers exist in the update
                    update.contains(rule.get(0)) && update.contains(rule.get(1)) &&
                            // If both numbers exist, ensure the first appears before the second
                            update.indexOf(rule.get(0)) >= update.indexOf(rule.get(1))
            );

            // If the update is safe, add its middle element to the sum
            if (isSafe) {
                int middleIndex = (update.size() - 1) / 2;
                sum += update.get(middleIndex);
            }
        }
        return sum;
    }

    private static List<List<Integer>> readFile(String fileName, String delimiter) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read each line, split by the delimiter, and convert to integers
            return reader.lines()
                    .map(line -> Arrays.stream(line.split(delimiter))
                            .map(Integer::valueOf)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        }
    }
}
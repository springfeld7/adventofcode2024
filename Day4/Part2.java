import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {

        // Read the input file into a 2D grid
        List<List<String>> wordSearch = readInput("input.txt");

        int occurrences = countOccurrences(wordSearch);
        System.out.println(occurrences);
    }

    private static int countOccurrences(List<List<String>> wordSearch) {
        int rows = wordSearch.size();
        int cols = wordSearch.get(0).size();
        int occurrences = 0;

        // Patterns to check
        String[][] patterns = {
                {"M", "M", "S", "S"},
                {"M", "S", "S", "M"},
                {"S", "S", "M", "M"},
                {"S", "M", "M", "S"}
        };

        // Loop through grid excluding borders
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (wordSearch.get(i).get(j).equals("A")) {
                    for (String[] pattern : patterns) {
                        if (matchesPattern(wordSearch, i, j, pattern)) {
                            occurrences++;
                            break; // No need to check further patterns for this cell
                        }
                    }
                }
            }
        }
        return occurrences;
    }

    private static boolean matchesPattern(List<List<String>> grid, int i, int j, String[] pattern) {
        return grid.get(i - 1).get(j - 1).equals(pattern[0]) && // Top-left
                grid.get(i + 1).get(j - 1).equals(pattern[1]) && // Bottom-left
                grid.get(i + 1).get(j + 1).equals(pattern[2]) && // Bottom-right
                grid.get(i - 1).get(j + 1).equals(pattern[3]);   // Top-right
    }

    private static List<List<String>> readInput(String fileName) throws IOException {
        List<List<String>> wordSearch = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordSearch.add(List.of(line.split("(?<=\\G.)")));
            }
        }
        return wordSearch;
    }
}

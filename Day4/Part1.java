import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    private static final String TARGET_WORD = "XMAS";
    private static final int WORD_LENGTH = TARGET_WORD.length();

    public static void main(String[] args) throws IOException {
        int occurrences = 0;

        // Read the input file into a 2D grid
        List<List<String>> wordSearch = readInput("input.txt");

        // Check for the target word in all directions
        int rows = wordSearch.size();
        int cols = wordSearch.get(0).size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (wordSearch.get(i).get(j).equals("X")) {
                    occurrences += countOccurrences(wordSearch, i, j);
                }
            }
        }

        System.out.println(occurrences);
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

    private static int countOccurrences(List<List<String>> grid, int row, int col) {
        int count = 0;
        int[][] directions = {
                {0, 1},  // Right
                {1, 0},  // Down
                {0, -1}, // Left
                {-1, 0}, // Up
                {1, 1},  // Diagonal Down-Right
                {-1, 1}, // Diagonal Up-Right
                {1, -1}, // Diagonal Down-Left
                {-1, -1} // Diagonal Up-Left
        };

        for (int[] dir : directions) {
            if (matchesWord(grid, row, col, dir[0], dir[1])) {
                count++;
            }
        }
        return count;
    }

    private static boolean matchesWord(List<List<String>> grid, int row, int col, int rowDir, int colDir) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        StringBuilder word = new StringBuilder();
        for (int k = 0; k < WORD_LENGTH; k++) {
            int newRow = row + k * rowDir;
            int newCol = col + k * colDir;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false; // Out of bounds
            }
            word.append(grid.get(newRow).get(newCol));
        }
        return word.toString().equals(TARGET_WORD);
    }
}

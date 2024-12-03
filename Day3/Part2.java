import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) throws IOException {
        // Define the pattern for matching "mul(x,y)"
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        int totalSum = 0;

        // Read the file content into a single string
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
            }
        }

        // Process each "do()" block separately
        String[] blocks = fileContent.toString().split("do\\(\\)");
        for (String block : blocks) {
            String enabledCode = block.split("don't\\(\\)", 2)[0]; // Only process code before "don't()"
            Matcher matcher = pattern.matcher(enabledCode);

            // Find all "mul(x,y)" matches and calculate their product
            while (matcher.find()) {
                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(2));
                totalSum += num1 * num2;
            }
        }

        System.out.println(totalSum);
    }
}

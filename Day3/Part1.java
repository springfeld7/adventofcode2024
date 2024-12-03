import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        int sumOfMultiplications = 0;

        // Read the entire file into a single string
        StringBuilder input = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line);
            }
        }

        // Find matches and compute the sum
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            int firstNumber = Integer.parseInt(matcher.group(1));
            int secondNumber = Integer.parseInt(matcher.group(2));
            sumOfMultiplications += firstNumber * secondNumber;
        }

        System.out.println(sumOfMultiplications);
    }
}

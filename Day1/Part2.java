import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Part2 {

    public static void main(final String... args) throws IOException {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        AtomicInteger similarityScore = new AtomicInteger();

        try (Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
            lines.map(line -> line.split("   "))
                    .forEach(parts -> {
                        list1.add(Integer.valueOf(parts[0]));
                        list2.add(Integer.valueOf(parts[1]));
                    });
        }

        list1.forEach(number -> {
            int occurrences = Collections.frequency(list2, number);
            similarityScore.addAndGet(number * occurrences);
        });

        System.out.println(similarityScore);
    }
}

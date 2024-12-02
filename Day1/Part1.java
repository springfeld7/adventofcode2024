import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Part1 {

    public static void main(final String... args) throws IOException {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        int sum = 0;

        try (Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
            lines.map(line -> line.split("   "))
                    .forEach(parts -> {
                        list1.add(Integer.valueOf(parts[0]));
                        list2.add(Integer.valueOf(parts[1]));
                    });
        }

        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());

        for (int i = 0; i < list1.size(); i++) {
            sum += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println(sum);
    }
}

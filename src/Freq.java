import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Freq: Enter a file path");
        String input = scanner.nextLine();

        try {
            String content = Files.readString(Paths.get(input)).toLowerCase(Locale.ROOT);
            String[] tokens = content.split(" ");
            Map<String, Long> elements = Arrays.stream(tokens)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            var result = elements.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()).limit(3);
            var resultArray = result.toList();
            for (int i = 0; i < resultArray.size() - 1; i++) {
                System.out.print(resultArray.get(i).getKey() + " ");
            }
            System.out.println(resultArray.get(resultArray.size() - 1).getKey());
        }
        catch (IOException e) {
            System.out.println("Unreadable file: ");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

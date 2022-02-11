import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }

    private String findCombination(String element, String[] tokens, int index) {
        List<String> combinations = new ArrayList<>();
        for (int i = index; i < tokens.length; i++) {
            if (element.equals(tokens[i]) && i + 1 < tokens.length) {
                combinations.add(tokens[i + 1]);
            }
        }

        if (combinations.size() == 0) {
            return null;
        }

        Map<String, Long> elements = combinations.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var sorted = elements.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()).limit(1);
        return sorted.toList().get(0).getKey();
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Predict: please enter a file path");
        String input = scanner.nextLine();
        Map<String, String> result = new HashMap<>();

        try {
            String content = Files.readString(Paths.get(input)).toLowerCase(Locale.ROOT);
            String[] tokens = content.split(" ");

            for (int i = 0; i < tokens.length; i++) {
                String currentMostFreq = findCombination(tokens[i], tokens, i);
                if (!result.containsKey(tokens[i])) {
                    result.put(tokens[i], currentMostFreq);
                }
            }

            System.out.println("Now enter a word contained in the file:");
            input = scanner.nextLine().toLowerCase(Locale.ROOT);

            System.out.print(input + " ");
            if (result.containsKey(input)) {
                String next = result.get(input);
                int i = 0;
                while (i < 19) {
                    System.out.print(next + " ");
                    next = result.get(next);
                    i++;
                }

                System.out.println(next);
            }
            else {
                System.out.println("The given word was not found in the file");
            }
        }
        catch (IOException e) {
            System.out.println("Unreadable file: ");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

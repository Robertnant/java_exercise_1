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

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Predict: please enter a file path");
        String input = scanner.nextLine();
        Map<String, String> result = new HashMap<>();

        try {
            String content = Files.readString(Paths.get(input)).toLowerCase(Locale.ROOT);
            String[] tokens = content.split(" ");

            for (int i = 0; i < tokens.length; i++) {
                String[] subtokens = Arrays.copyOfRange(tokens, i, tokens.length);
                Map<String, Long> elements = Arrays.stream(subtokens)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                var sorted = elements.entrySet()
                        .stream()
                        .sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()).limit(1);
                result.put(tokens[i], sorted.toList().get(0).getKey());
            }

            System.out.println("Now enter a word contained in the file:");
            input = scanner.nextLine();

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

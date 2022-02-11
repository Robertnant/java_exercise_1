import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome people!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "quit":
                    System.out.println("See you next time!");
                    return;
                case "freq":
                    System.out.println("Freq: Enter a file path");
                    input = scanner.nextLine();

                    try {
                        String content = Files.readString(Paths.get(input));
                        String[] tokens = content.split(" ");
                        Map<String, Long> elements = Arrays.stream(tokens)
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                        var result = elements.entrySet()
                                .stream()
                                .sorted(Comparator.comparing(Map.Entry<String, Long>::getValue)).limit(3);
                        var resultArray = result.toList();
                        for (int i = 0; i < resultArray.size() - 1; i++) {
                            System.out.print(resultArray.get(i).getKey() + " ");
                        }
                        System.out.println(resultArray.get(resultArray.size() - 1).getKey());
                    }
                    catch (IOException e) {
                        System.out.println("Unreadable file: ");
                        e.printStackTrace();
                    }
                    break;
                case "fibo":
                    System.out.println("Fibonacci: Enter a number");
                    int number = scanner.nextInt();

                    // Skip new line not consumed by nextInt method in scanner.
                    scanner.nextLine();

                    if (number == 0) {
                        System.out.println(number);
                        break;
                    }

                    int f0 = 0, f1 = 1, f2 = 0;

                    for (int i = 0; i < number - 1; i++) {
                        f2 = f0 + f1;
                        f0 = f1;
                        f1 = f2;
                    }

                    System.out.println(f1);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}

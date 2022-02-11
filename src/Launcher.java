import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome people!");

        // List of different available commands
        List<Command> commands = List.of(new Quit(), new Freq(), new Fibo(), new Predict());

        while (true) {
            // Get command from user and find in available commands list
            String input = scanner.nextLine();

            int i;
            for (i = 0; i < commands.size(); i++) {
                Command command = commands.get(i);
                if (input.equals(command.name())) {
                    if (command.run(scanner)) {
                        return;
                    }
                    break;
                }
            }

            if (i == commands.size()) {
                System.out.println("Unknown command");
            }
        }
    }
}

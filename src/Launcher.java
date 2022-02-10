import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome people!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "quit":
                    return;
                case "fibo":
                    System.out.println("Fibonacci: Enter a number");
                    int number = scanner.nextInt();
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

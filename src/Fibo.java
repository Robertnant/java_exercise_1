import java.util.Scanner;

public class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Fibonacci: Enter a number");
        int number = scanner.nextInt();

        // Skip new line not consumed by nextInt method in scanner.
        scanner.nextLine();

        if (number < 0) {
            System.out.println("Number given is not valid");
            return false;
        }
        else if (number == 0) {
            System.out.println(number);
            return true;
        }

        int f0 = 0, f1 = 1, f2 = 0;

        for (int i = 0; i < number - 1; i++) {
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }

        System.out.println(f1);
        return true;
    }
}

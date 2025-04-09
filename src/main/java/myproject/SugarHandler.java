package myproject;

import java.util.Scanner;

public class SugarHandler implements Handler {
    private Handler next;
    private final Scanner scanner;

    public SugarHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public boolean handle(String input) {
        System.out.println("How many sugars? (0-3):");
        try {
            int sugarAmount = Integer.parseInt(scanner.nextLine());
            if (sugarAmount >= 0 && sugarAmount <= 3) {
                return true; // Передаем управление следующему обработчику, если нужно
            } else {
                System.out.println("Invalid sugar amount. Using default (0).");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default (0).");
            return false;
        }
    }
}
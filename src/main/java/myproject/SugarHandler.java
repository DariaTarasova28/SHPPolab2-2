// SugarHandler.java
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
        while (true) {
            System.out.println("How many sugars (0-3)?");
            String sugarInput = scanner.nextLine();

            if (next != null && next.handle(sugarInput)) {
                return true;
            } else {
                System.out.println("Please enter a valid number between 0 and 3.");
            }
        }
    }
}

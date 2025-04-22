package myproject;

import java.util.Scanner;

public class SugarHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public boolean handle(Drink drink, Scanner scanner) {
        System.out.println("Add sugar? (yes/no):");
        String sugarChoice = scanner.next().toLowerCase();

        if ("yes".equals(sugarChoice)) {
            System.out.println("How many sugars (0-3)?");
            int sugars = -1;
            while (sugars < 0 || sugars > 3) {
                if (scanner.hasNextInt()) {
                    sugars = scanner.nextInt();
                    if (sugars >= 0 && sugars <= 3) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 0 and 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 0 and 3.");
                    scanner.next(); // clear invalid input
                }
            }
            drink = new DrinkWithSugar(drink, sugars);
        } else {
            System.out.println("No sugar added.");
        }

        if (next != null) {
            return next.handle(drink, scanner);
        }
        return true;
    }
}

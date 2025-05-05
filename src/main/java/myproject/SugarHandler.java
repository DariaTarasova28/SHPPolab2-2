package myproject;


import java.util.Scanner;


public class SugarHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public Drink handle(Drink drink, Scanner scanner) {
        System.out.print("Add sugar? (yes/no): ");
        String sugarChoice = scanner.next().toLowerCase();

        if ("yes".equals(sugarChoice)) {
            int sugars = -1;
            while (sugars < 0 || sugars > 3) {
                System.out.print("How many sugars (0-3)? ");
                if (scanner.hasNextInt()) {
                    sugars = scanner.nextInt();
                } else {
                    scanner.next();
                }
            }
            drink = new DrinkWithSugar(drink, sugars);
            System.out.println(sugars + " sugar(s) added");
        }

        if (next != null) {
            return next.handle(drink, scanner);
        }
        return drink;
    }
}
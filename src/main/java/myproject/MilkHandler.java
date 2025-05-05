package myproject;


import java.util.Scanner;


public class MilkHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public Drink handle(Drink drink, Scanner scanner) {
        System.out.print("Add milk? (yes/no): ");
        String milkChoice = scanner.next().toLowerCase();

        if ("yes".equals(milkChoice)) {
            drink = new DrinkWithMilk(drink);
            System.out.println("Milk added");
        }

        if (next != null) {
            return next.handle(drink, scanner);
        }
        return drink;
    }
}
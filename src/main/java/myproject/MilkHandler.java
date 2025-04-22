package myproject;
import java.util.Scanner;
public class MilkHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }

    @Override
    public boolean handle(Drink drink, Scanner scanner) {
        if (drink instanceof Coffee) { // Молоко только для кофе
            System.out.println("Add milk? (yes/no):");
            String milkChoice = scanner.next().toLowerCase();

            if ("yes".equals(milkChoice)) {
                // Обновляем объект drink, добавляя молоко
                drink = new CoffeeWithMilk(drink);  // Создаем новый объект с молоком
                System.out.println("Milk added.");
            } else {
                System.out.println("No milk added.");
            }
        }

        if (next != null) {
            return next.handle(drink, scanner);
        }
        return true;
    }
}




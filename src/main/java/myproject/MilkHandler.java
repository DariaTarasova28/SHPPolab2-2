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
        if (drink instanceof Coffee) {
            System.out.println("Add milk? (yes/no):");
            String milkChoice = scanner.next().toLowerCase();

            if ("yes".equals(milkChoice)) {
                drink = new DrinkWithMilk(drink); // Применяем декоратор
                System.out.println("Milk added");
            }
        }

        if (next != null) {
            return next.handle(drink, scanner);
        }
        return drink; // Возвращаем модифицированный напиток
    }
}
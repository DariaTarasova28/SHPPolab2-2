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
        System.out.println("Add sugar? (yes/no):");
        String sugarChoice = scanner.next().toLowerCase();

        if ("yes".equals(sugarChoice)) {
            System.out.println("How many sugars (0-3)?");
            int sugars = -1;
            while (sugars < 0 || sugars > 3) {
                if (scanner.hasNextInt()) {
                    sugars = scanner.nextInt();
                }
                scanner.nextLine(); // Очищаем буфер
            }
            drink = new DrinkWithSugar(drink, sugars); // Применяем декоратор
            System.out.println(sugars + " sugar(s) added");
        }

        if (next != null) {
            return next.handle(drink, scanner);
        }
        return drink; // Возвращаем модифицированный напиток
    }
}
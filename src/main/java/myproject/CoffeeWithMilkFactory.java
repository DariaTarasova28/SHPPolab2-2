package myproject;
import java.util.Scanner;

class CoffeeWithMilkFactory implements DrinkFactory {
    @Override
    public Drink getDrink(String manufacturer) {
        CoffeeFactory cf = new CoffeeFactory();
        // Создаем напиток с фабрики кофе
        Drink drink = cf.getDrink(manufacturer);

        // Создаем цепочку обязанностей для молока
        Handler milkHandler = new MilkHandler();

        // Обрабатываем добавку молока
        if (drink instanceof Coffee && milkHandler.handle(drink, new Scanner(System.in))) {
            // Если напиток — кофе, добавляем молоко
            return new CoffeeWithMilk(drink);
        }
        return drink;
    }
}

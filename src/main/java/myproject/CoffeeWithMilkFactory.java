package myproject;
import java.util.Scanner;

class CoffeeWithMilkFactory implements DrinkFactory {
    @Override
    public Drink getDrink(String manufacturer) {
        CoffeeFactory cf = new CoffeeFactory();

        Drink drink = cf.getDrink(manufacturer);


        Handler milkHandler = new MilkHandler();


        if (drink instanceof Coffee && milkHandler.handle(drink, new Scanner(System.in))) {

            return new CoffeeWithMilk(drink);
        }
        return drink;
    }
}

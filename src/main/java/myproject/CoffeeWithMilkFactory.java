package myproject;
import java.util.Scanner;

// Фабрика для создания кофе с молоком
class CoffeeWithMilkFactory implements DrinkFactory {
    public CoffeeWithMilkFactory(){
    }
    @Override
    public Drink getDrink(String manufacturer) {
        CoffeeFactory cf = new CoffeeFactory();
        Handler milkHandler = new MilkHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wanna Milk?");
        if(milkHandler.handle(scanner.next()))
            return new CoffeeWithMilk(cf.getDrink(manufacturer));
        else return cf.getDrink(manufacturer);
    }
}
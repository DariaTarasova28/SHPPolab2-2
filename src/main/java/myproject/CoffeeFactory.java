package myproject;
// Кофе
public class CoffeeFactory implements DrinkFactory {
    @Override
    public Drink getDrink(String manufacturer) {
        return new Coffee(manufacturer, 1.5);
    }
}
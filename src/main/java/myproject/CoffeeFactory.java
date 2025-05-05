package myproject;

import org.springframework.stereotype.Component;

@Component("coffeeFactory")
public class CoffeeFactory implements DrinkFactory {


    @Override
    public Drink getDrink(String manufacturer) {
        final double basePrice = 1.5;
        return new Coffee(manufacturer, basePrice);
    }
}
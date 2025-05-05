package myproject;

import org.springframework.stereotype.Component;

@Component("greenTeaFactory")

public class GreenTeaFactory implements DrinkFactory {


    @Override
    public Drink getDrink(String manufacturer) {
        final double basePrice = 1.5;
        return new GreenTea(manufacturer, basePrice);
    }
}
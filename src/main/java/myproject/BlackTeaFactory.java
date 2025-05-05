package myproject;
import org.springframework.stereotype.Component;

@Component("blackTeaFactory")
public class BlackTeaFactory implements DrinkFactory {
    @Override
    public Drink getDrink(String manufacturer) {
        final double basePrice = 1.2;
        return new BlackTea(manufacturer, basePrice);
    }
}
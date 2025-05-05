package myproject;
import org.springframework.stereotype.Component;
// Фабрика для создания черного чая
@Component("blackTeaFactory") // Объявляем как компонент Spring
public class BlackTeaFactory implements DrinkFactory {

    // Можно вынести в конфиг

    @Override
    public Drink getDrink(String manufacturer) {
        final double basePrice = 1.2;
        return new BlackTea(manufacturer, basePrice);
    }
}
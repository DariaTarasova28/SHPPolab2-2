package myproject;
// Фабрика для создания черного чая
public class BlackTeaFactory implements DrinkFactory {
    @Override
    public Drink getDrink(String manufacturer) {
        return new BlackTea(manufacturer, 1.2);
    }
}
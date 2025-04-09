package myproject;
// Фабрика для создания зеленого чая
public class GreenTeaFactory implements DrinkFactory {
    @Override
    public Drink getDrink(String manufacturer) {
        return new GreenTea(manufacturer, 1.5);
    }
}
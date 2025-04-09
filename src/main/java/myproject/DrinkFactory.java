package myproject;
// Фабрика для создания напитков
public interface DrinkFactory {
    Drink getDrink(String manufacturer);
}
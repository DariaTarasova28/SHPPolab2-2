package myproject;
import java.util.Scanner;

public interface Handler {
    void setNext(Handler handler);
    Drink handle(Drink drink, Scanner scanner); // Теперь возвращаем Drink
}
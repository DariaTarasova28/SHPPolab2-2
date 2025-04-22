package myproject;
import java.util.Scanner;
public interface Handler {
    void setNext(Handler handler);
    boolean handle(Drink drink, Scanner scanner);
}


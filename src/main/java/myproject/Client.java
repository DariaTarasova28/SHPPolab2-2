package myproject;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private final String clientId;
    private final List<Drink> drinks;

    public Client(String clientId) {
        this.clientId = clientId;
        this.drinks = new ArrayList<>();
    }

    // Новый метод для добавления напитка в список клиента
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    // Получить все напитки клиента
    public List<Drink> getDrinks() {
        return drinks;
    }

    public String getClientId() {
        return clientId;
    }
}




package myproject;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private static int idCounter = 0;
    private final int clientId;
    private final List<Drink> drinks = new ArrayList<>();

    public Client() {
        this.clientId = idCounter++;
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
        Order order = new Order(this, drink);
        DrinkMachine.addOrder(order);
    }

    public int getClientId() {
        return clientId;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}



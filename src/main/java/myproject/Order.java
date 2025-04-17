package myproject;

public class Order {
    private final Client client;
    private final Drink drink;

    public Order(Client client, Drink drink) {
        this.client = client;
        this.drink = drink;
    }

    public Client getClient() {
        return client;
    }

    public Drink getDrink() {
        return drink;
    }
}



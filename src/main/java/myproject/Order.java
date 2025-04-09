package myproject;

public class Order {
    public final Client client;
    public final Drink drink;

    public Order(Client client, Drink drink) {
        this.client = client;
        this.drink = drink;
    }
}

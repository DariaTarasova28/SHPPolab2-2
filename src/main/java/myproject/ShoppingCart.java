package myproject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Drink> drinks = new ArrayList<>();

    public Drink addDrink(DrinkFactory factory, String manufacturer) {
        Drink drink = factory.getDrink(manufacturer);
        drinks.add(drink);
        return drink;
    }

    public double calculateTotal() {
        return drinks.stream().mapToDouble(Drink::calculateCost).sum();
    }

    public void pay(PaymentStrategy strategy) {
        double total = calculateTotal();
        System.out.println("Using payment method: " + strategy.getPaymentDetails());
        strategy.pay(total);
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}

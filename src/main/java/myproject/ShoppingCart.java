package myproject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Drink> drinks = new ArrayList<>();

    // Метод addDrink теперь принимает только Drink, так как мы уже создаём Drink через фабрики в Menu
    public void addDrink(Drink drink) {
        drinks.add(drink);
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


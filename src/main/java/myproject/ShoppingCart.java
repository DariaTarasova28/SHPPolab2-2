package myproject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Drink> drinks = new ArrayList<>();


    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public double calculateTotal() {
        return drinks.stream().mapToDouble(Drink::calculateCost).sum();
    }

    public void pay(PaymentStrategyProxy strategy) {
        double total = calculateTotal();
        System.out.println("Using payment method: " + strategy.getPaymentDetails());
        strategy.pay(total);
    }


}


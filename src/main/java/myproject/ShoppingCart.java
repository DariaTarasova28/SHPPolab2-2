package myproject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Drink> drinks = new ArrayList<>();

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Drink drink : drinks) {
            total += drink.getCost(); // Используем getCost() который учитывает все добавки
        }
        return total;
    }

    public void pay(PaymentStrategyProxy strategy) {
        double total = calculateTotal();
        System.out.println("Using payment method: " + strategy.getPaymentDetails());
        System.out.println("Total cost with all additions: $" + total);
        strategy.pay(total);
    }
}


package myproject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Drink> drinks = new ArrayList<>();

    // Добавление напитка
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    // Подсчёт общей стоимости
    public double calculateTotal() {
        return drinks.stream().mapToDouble(Drink::calculateCost).sum();
    }

    // Оплата с использованием стратегии
    public void pay(PaymentStrategy strategy) {
        double total = calculateTotal();
        System.out.println("Using payment method: " + strategy.getPaymentDetails());
        strategy.pay(total);
        clearCart();
    }


    public List<Drink> getDrinks() {
        return drinks;
    }


    public void clearCart() {
        drinks.clear();
    }
}

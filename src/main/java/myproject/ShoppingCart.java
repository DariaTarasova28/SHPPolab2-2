package myproject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype") // Добавляем аннотацию
public class ShoppingCart {
    private final List<Drink> drinks = new ArrayList<>();

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public double calculateTotal() {
        return drinks.stream()
                .mapToDouble(Drink::getCost)
                .sum();
    }

    public void pay(PaymentStrategyProxy strategy) {
        double total = calculateTotal();
        System.out.println("Using payment method: " + strategy.getPaymentDetails());
        System.out.println("Total cost with all additions: $" + total);
        strategy.pay(total);
        drinks.clear();
    }
}



package myproject;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PaymentStrategyProxy implements PaymentStrategy {
    private PaymentStrategy currentStrategy;
    private final List<PaymentStrategy> availableStrategies;
    private final Scanner scanner;

    @Autowired
    public PaymentStrategyProxy(List<PaymentStrategy> availableStrategies,
                                Scanner scanner) {
        this.availableStrategies = availableStrategies;
        this.scanner = scanner;
        this.currentStrategy = availableStrategies.get(0); // default strategy
    }

    public PaymentStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    public void selectStrategy(int choice) {
        if (choice >= 1 && choice <= availableStrategies.size()) {
            this.currentStrategy = availableStrategies.get(choice - 1);
        } else {
            this.currentStrategy = availableStrategies.get(availableStrategies.size() - 1); // soul
        }
    }

    @Override
    public void pay(double amount) {
        Optional<String> failureReason = currentStrategy.canPay(amount);
        if (failureReason.isEmpty()) {
            currentStrategy.pay(amount);
        } else {
            System.out.println("[FAILED] " + failureReason.get());
            switchToAlternative(amount);
        }
    }

    private void switchToAlternative(double amount) {
        System.out.println("\nSelect alternative payment method:");
        for (int i = 0; i < availableStrategies.size(); i++) {
            System.out.println((i + 1) + ". " +
                    availableStrategies.get(i).getClass().getSimpleName());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= availableStrategies.size()) {
            selectStrategy(choice);
            currentStrategy.init(scanner);
            pay(amount); // рекурсивный вызов с новой стратегией
        } else {
            System.out.println("Invalid choice. Payment canceled.");
        }
    }

    @Override
    public Optional<String> canPay(double amount) {
        return currentStrategy.canPay(amount);
    }

    @Override
    public String getPaymentDetails() {
        return currentStrategy.getPaymentDetails();
    }

    @Override
    public void init(Scanner scanner) {
        currentStrategy.init(scanner);
    }
}
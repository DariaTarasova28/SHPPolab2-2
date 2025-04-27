package myproject;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PaymentStrategyProxy implements PaymentStrategy {
    private PaymentStrategy currentStrategy;
    private final List<PaymentStrategy> availableStrategies;
    private final Scanner scanner;

    public PaymentStrategyProxy(PaymentStrategy initialStrategy,
                                List<PaymentStrategy> availableStrategies,
                                Scanner scanner) {
        this.currentStrategy = initialStrategy;
        this.availableStrategies = availableStrategies;
        this.scanner = scanner;
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
        scanner.nextLine(); // Очистка буфера

        if (choice >= 1 && choice <= availableStrategies.size()) {
            PaymentStrategy newStrategy = availableStrategies.get(choice - 1);
            newStrategy.init(scanner);
            new PaymentStrategyProxy(newStrategy, availableStrategies, scanner).pay(amount);
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
        // Прокси не требует дополнительной инициализации
    }
}
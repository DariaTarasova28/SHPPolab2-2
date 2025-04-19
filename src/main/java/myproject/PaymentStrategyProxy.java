package myproject;

import java.time.LocalTime;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class PaymentStrategyProxy implements PaymentStrategy {
    private PaymentStrategy realStrategy;
    private final List<PaymentStrategy> availableStrategies;

    public PaymentStrategyProxy(PaymentStrategy realStrategy, List<PaymentStrategy> allStrategies) {
        this.realStrategy = realStrategy;
        this.availableStrategies = allStrategies;
    }

    @Override
    public String getPaymentDetails() {
        System.out.println("[LOG] Получение информации об оплате: " + realStrategy.getClass().getSimpleName());
        return realStrategy.getPaymentDetails();
    }

    @Override
    public void pay(double amount) {
        if (tryPay(realStrategy, amount)) {
            return;
        }

        // Если оплата не прошла — предложим альтернативу
        System.out.println("\n[INFO] Выберите другой способ оплаты: ");
        for (int i = 0; i < availableStrategies.size(); i++) {
            PaymentStrategy strategy = availableStrategies.get(i);
            if (!strategy.getClass().equals(realStrategy.getClass())) {
                System.out.println((i + 1) + ". " + strategy.getClass().getSimpleName());
            }
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= availableStrategies.size()) {
            PaymentStrategy newStrategy = availableStrategies.get(choice - 1);
            System.out.println("[LOG] Вы выбрали новый способ оплаты: " + newStrategy.getClass().getSimpleName());
            new PaymentStrategyProxy(newStrategy, availableStrategies).pay(amount);
        } else {
            System.out.println("[BUG] Неверный выбор. Оплата отменена.");
        }
    }

    private boolean tryPay(PaymentStrategy strategy, double amount) {
        System.out.println("[LOG] Попытка оплаты: " + amount + " с использованием " + strategy.getClass().getSimpleName());

        if (strategy instanceof SoulPaymentStrategy) {
            LocalTime now = LocalTime.now();
            if (now.isAfter(LocalTime.of(22, 0)) || now.isBefore(LocalTime.of(6, 0))) {
                System.out.println("[ОТКАЗ] Оплата душой недоступна в ночное время (22:00–06:00).");
                return false;
            }
        }

        if (strategy instanceof DebitCardStrategy && amount > 5) {
            System.out.println("[ОТКАЗ] Превышен лимит по дебетовой карте: максимум 5.");
            return false;
        }

        if (strategy instanceof CreditCardStrategy && amount > 10) {
            System.out.println("[WARNING] Оплата кредитной картой более 10. Проверьте безопасность оплаты.");
        }

        // Успешная оплата
        strategy.pay(amount);
        return true;
    }
}

package myproject;

import java.util.Optional;
import java.util.Scanner;

public class DebitCardStrategy implements PaymentStrategy {
    private String cardNumber;

    // Конструктор без параметров
    public DebitCardStrategy() {
        this.cardNumber = null; // Будет установлен в init()
    }

    @Override
    public void init(Scanner scanner) {
        System.out.print("Enter debit card number: ");
        this.cardNumber = scanner.nextLine();
    }

    @Override
    public Optional<String> canPay(double amount) {
        return amount > 5
                ? Optional.of("Debit card limit exceeded: max $5 allowed")
                : Optional.empty();
    }

    @Override
    public String getPaymentDetails() {
        return "debit card #" + cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using debit card #" + cardNumber);
    }
}

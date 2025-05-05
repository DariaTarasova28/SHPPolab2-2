package myproject;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;


@Component
@Scope("singleton")
public class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;

    @Override
    public void init(Scanner scanner) {
        System.out.print("Enter credit card number: ");
        this.cardNumber = scanner.nextLine();
    }

    @Override
    public Optional<String> canPay(double amount) {
        if (amount > 10) {
            return Optional.of("Warning: Credit card payment over $10");
        }
        return Optional.empty();
    }

    @Override
    public String getPaymentDetails() {
        return "credit card #" + cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using credit card #" + cardNumber);
    }
}

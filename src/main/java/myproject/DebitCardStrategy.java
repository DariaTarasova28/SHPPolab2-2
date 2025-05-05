package myproject;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.Optional;
import java.util.Scanner;

@Component
@Scope("singleton")
public class DebitCardStrategy implements PaymentStrategy {
    private String cardNumber;

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

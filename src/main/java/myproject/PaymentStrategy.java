package myproject;
import java.util.Optional;
import java.util.Scanner;

public interface PaymentStrategy {
    void pay(double amount);
    String getPaymentDetails();
    Optional<String> canPay(double amount);
    void init(Scanner scanner);
}
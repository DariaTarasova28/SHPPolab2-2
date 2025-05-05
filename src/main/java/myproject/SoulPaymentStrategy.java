package myproject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

@Component
@Scope("singleton")
public class SoulPaymentStrategy implements PaymentStrategy {
    @Override
    public void init(Scanner scanner) {
        // Не требует ввода данных
    }

    @Override
    public Optional<String> canPay(double amount) {
        LocalTime now = LocalTime.now();
        if (now.isAfter(LocalTime.of(22, 0)) || now.isBefore(LocalTime.of(6, 0))) {
            return Optional.of("Soul payment unavailable (22:00-06:00)");
        }
        return Optional.empty();
    }

    @Override
    public String getPaymentDetails() {
        return "YOUR SOUL";
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment received. You no longer own your soul.");
    }
}


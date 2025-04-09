package myproject;

public class SoulPaymentStrategy implements PaymentStrategy {

    @Override
    public String getPaymentDetails() {
        return "YOUR SOUL";
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment received. You no longer own your soul.");
    }
}

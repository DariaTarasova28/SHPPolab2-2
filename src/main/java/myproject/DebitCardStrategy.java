package myproject;

public class DebitCardStrategy implements PaymentStrategy {
    private final String cardNumber;

    public DebitCardStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
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


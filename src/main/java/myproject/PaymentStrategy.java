package myproject;
// Посетитель, который заказывает напитки
public interface PaymentStrategy {
    void pay(double amount);
    String getPaymentDetails();
}
package myproject;

public class PaymentStrategyProxy implements PaymentStrategy {
    PaymentStrategy paymentStrategy;

    public PaymentStrategyProxy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    @Override
    public String getPaymentDetails(){
        return paymentStrategy.getPaymentDetails();
    }

    @Override
    public void pay(double amount) {
        System.out.println("Calling pay(" + amount + ")");
        paymentStrategy.pay(amount);
        System.out.println("$" + amount + " successfully paid with " + paymentStrategy.getPaymentDetails());
    }
}



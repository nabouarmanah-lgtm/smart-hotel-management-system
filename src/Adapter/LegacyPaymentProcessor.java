package Adapter;

public class LegacyPaymentProcessor {
    public void makePayment(String cardNumber, double amount) {
        System.out.println("Legacy System: Processing payment of $" + amount + " using card: " + cardNumber);
    }
}
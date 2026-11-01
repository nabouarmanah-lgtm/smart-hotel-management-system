package Adapter;

public class PaymentAdapter implements PaymentProcessor {
    private LegacyPaymentProcessor legacy;
    private String cardNumber;

    public PaymentAdapter(LegacyPaymentProcessor legacy, String cardNumber) {
        this.legacy = legacy;
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Adapter: Converting new payment request to legacy format...");
        legacy.makePayment(cardNumber, amount);
    }
}
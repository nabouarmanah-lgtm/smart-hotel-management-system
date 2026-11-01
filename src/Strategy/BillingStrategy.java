package Strategy;

public interface BillingStrategy {
    double calculateBill(double basePrice, int nights);
}
package Strategy;

public class RegularPricing implements BillingStrategy {
    @Override
    public double calculateBill(double basePrice, int nights) {
        System.out.println("Billing Strategy: Regular Pricing");
        return basePrice * nights;
    }
}
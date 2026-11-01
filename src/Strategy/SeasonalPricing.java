package Strategy;

public class SeasonalPricing implements BillingStrategy {
    @Override
    public double calculateBill(double basePrice, int nights) {
        System.out.println("Billing Strategy: Seasonal Pricing (30% extra)");
        return basePrice * nights * 1.30;
    }
}
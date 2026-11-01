package Strategy;

public class MemberDiscount implements BillingStrategy {
    @Override
    public double calculateBill(double basePrice, int nights) {
        System.out.println("Billing Strategy: Member Discount (20% off)");
        return basePrice * nights * 0.80;
    }
}
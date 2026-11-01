package Strategy;

public class BillingContext {
    private BillingStrategy strategy;

    public BillingContext(BillingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(BillingStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(double basePrice, int nights) {
        return strategy.calculateBill(basePrice, nights);
    }
}
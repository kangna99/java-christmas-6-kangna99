package christmas.event;

public class SpecialDiscountEvent implements DiscountEvent {
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public boolean isApplicableDay(int visitDate) {
        return visitDate == 3 || visitDate == 10 || visitDate == 17 || visitDate == 24 || visitDate == 25
                || visitDate == 31;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        return DISCOUNT_AMOUNT;
    }

    @Override
    public String name() {
        return "특별 할인";
    }
}

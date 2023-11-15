package christmas.event;

public class SpecialDiscountEvent implements DiscountEvent {
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public boolean isApplicable(Customer customer) {
        return DiscountEvent.super.isApplicable(customer) && additionalCondition(customer);
    }

    private boolean additionalCondition(Customer customer) {
        int dayOfMonth = customer.getVisitDate();
        return dayOfMonth == 3 || dayOfMonth == 10 || dayOfMonth == 17 || dayOfMonth == 24 || dayOfMonth == 25
                || dayOfMonth == 31;
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

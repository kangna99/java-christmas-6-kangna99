package christmas.event;

public class ChristmasDDayDiscountEvent implements DiscountEvent {
    private static final int INITIAL_DISCOUNT = 1_000;
    private static final int DAILY_INCREASE_AMOUNT = 100;
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;

    @Override
    public boolean isApplicable(Customer customer) {
        return DiscountEvent.super.isApplicable(customer) && additionalCondition(customer);
    }

    private boolean additionalCondition(Customer customer) {
        int visitDate = customer.getVisitDate();
        return visitDate >= START_DAY && visitDate <= END_DAY;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        int dayCount = customer.getVisitDate() - START_DAY;
        return INITIAL_DISCOUNT + dayCount * DAILY_INCREASE_AMOUNT;
    }

    @Override
    public String name() {
        return "크리스마스 디데이 할인";
    }
}

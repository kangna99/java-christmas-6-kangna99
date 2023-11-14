package christmas.events;

import christmas.Customer;

public class ChristmasDDayDiscountEvent implements DiscountEvent {
    private static final int INITIAL_DISCOUNT = 1000;
    private static final int DAILY_INCREASE = 100;
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;

    @Override
    public boolean isApplicable(Customer customer) {
        int visitDate = customer.getVisitDate();
        return visitDate >= START_DAY && visitDate <= END_DAY;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        int daysFromStart = customer.getVisitDate() - START_DAY;
        return INITIAL_DISCOUNT + daysFromStart * DAILY_INCREASE;
    }
}

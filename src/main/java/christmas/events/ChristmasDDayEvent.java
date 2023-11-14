package christmas.events;

import christmas.Customer;
import java.time.LocalDate;

public class ChristmasDDayEvent implements Event {
    private static final int INITIAL_DISCOUNT = 1000;
    private static final int DAILY_INCREASE = 100;
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;

    @Override
    public boolean isApplicable(LocalDate date) {
        return date.getDayOfMonth() >= START_DAY && date.getDayOfMonth() <= END_DAY;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        int daysFromStart = customer.getVisitDate() - START_DAY;
        return INITIAL_DISCOUNT + daysFromStart * DAILY_INCREASE;
    }
}

package christmas.events;

import christmas.Customer;
import java.time.LocalDate;

public class SpecialEvent implements Event {
    private static final int DISCOUNT_AMOUNT = 1000;

    @Override
    public boolean isApplicable(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        return dayOfMonth == 3 || dayOfMonth == 10 || dayOfMonth == 17 || dayOfMonth == 24 || dayOfMonth == 25
                || dayOfMonth == 31;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        return DISCOUNT_AMOUNT;
    }
}

package christmas.events;

import christmas.Customer;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendEvent implements Event {
    private static final int DISCOUNT_AMOUNT_PER_MAIN_DISH = 2023;

    @Override
    public boolean isApplicable(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        int mainDishCount = customer.getOrderDetails().getMainDishCount();
        return DISCOUNT_AMOUNT_PER_MAIN_DISH * mainDishCount;
    }
}

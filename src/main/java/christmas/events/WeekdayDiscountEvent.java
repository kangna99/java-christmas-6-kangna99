package christmas.events;

import christmas.Customer;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountEvent implements DiscountEvent {
    private static final int DISCOUNT_AMOUNT_PER_DESSERT = 2023;

    @Override
    public boolean isApplicable(Customer customer) {
        LocalDate date = LocalDate.of(2023, 12, customer.getVisitDate());
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        int dessertCount = customer.getOrderDetails().getDessertCount();
        return DISCOUNT_AMOUNT_PER_DESSERT * dessertCount;
    }
}

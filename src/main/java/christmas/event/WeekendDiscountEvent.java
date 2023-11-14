package christmas.event;

import christmas.Customer;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountEvent implements DiscountEvent {
    private static final int DISCOUNT_AMOUNT_PER_MAIN_DISH = 2_023;

    @Override
    public boolean isApplicable(Customer customer) {
        return DiscountEvent.super.isApplicable(customer) && additionalCondition(customer);
    }

    public boolean additionalCondition(Customer customer) {
        LocalDate date = LocalDate.of(2023, 12, customer.getVisitDate());
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    @Override
    public int calculateDiscount(Customer customer) {
        int mainDishCount = customer.getOrderDetails().getMainDishCount();
        return DISCOUNT_AMOUNT_PER_MAIN_DISH * mainDishCount;
    }

    @Override
    public String name() {
        return "주말 할인";
    }
}
package christmas.event;

import christmas.Customer;

public interface DiscountEvent {
    default boolean isApplicable(Customer customer) {
        return customer.getOrderDetails().calculateTotalPrice() >= 10_000;
    }

    int calculateDiscount(Customer customer);

    String name();
}
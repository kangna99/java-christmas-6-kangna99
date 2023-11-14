package christmas.events;

import christmas.Customer;

public interface DiscountEvent {
    boolean isApplicable(Customer customer);

    int calculateDiscount(Customer customer);

    String name();
}

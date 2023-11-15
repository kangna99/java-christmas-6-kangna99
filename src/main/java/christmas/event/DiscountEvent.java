package christmas.event;

public interface DiscountEvent {
    int MINIMUM_APPLICABLE_AMOUNT = 10_000;

    default boolean isApplicable(Customer customer) {
        return customer.getOrderDetails().calculateTotalPrice() >= MINIMUM_APPLICABLE_AMOUNT;
    }

    int calculateDiscount(Customer customer);

    String name();
}
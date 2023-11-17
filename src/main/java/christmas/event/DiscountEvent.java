package christmas.event;

public interface DiscountEvent {
    int MINIMUM_APPLICABLE_AMOUNT = 10_000;

    default boolean isApplicable(int totalPrice, int visitDate) {
        return isApplicableAmount(totalPrice) && isApplicableDay(visitDate);
    }

    default boolean isApplicableAmount(int totalPrice) {
        return totalPrice >= MINIMUM_APPLICABLE_AMOUNT;
    }

    boolean isApplicableDay(int visitDate);

    int calculateDiscount(Customer customer);

    String name();
}
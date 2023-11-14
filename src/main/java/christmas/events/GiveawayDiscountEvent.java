package christmas.events;

import static christmas.Menu.CHAMPAGNE;

import christmas.Customer;

public class GiveawayDiscountEvent {
    private static final int MINIMUM_APPLICABLE_AMOUNT = 10_000;

    private boolean isApplicable(Customer customer) {
        return customer.getOrderDetails().calculateTotalPrice() >= MINIMUM_APPLICABLE_AMOUNT;
    }

    public int calculateBenefitAmount(Customer customer) {
        if (isApplicable(customer)) {
            return CHAMPAGNE.getPrice();
        }
        return 0;
    }
}

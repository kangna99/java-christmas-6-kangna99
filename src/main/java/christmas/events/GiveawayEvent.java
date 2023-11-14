package christmas.events;

import static christmas.Menu.CHAMPAGNE;

import christmas.Customer;
import christmas.Menu;

public class GiveawayEvent {
    private static final int MINIMUM_APPLICABLE_AMOUNT = 10_000;

    public boolean isApplicable(Customer customer) {
        return customer.getOrderDetails().calculateTotalPrice() >= MINIMUM_APPLICABLE_AMOUNT;
    }

    public int calculateBenefitAmount(Customer customer) {
        if (isApplicable(customer)) {
            return item().getPrice();
        }
        return 0;
    }

    public String eventName() {
        return "증정 이벤트";
    }

    public Menu item() {
        return CHAMPAGNE;
    }

    public int itemCount() {
        return 1;
    }
}

package christmas.event;

import static christmas.menu.Menu.CHAMPAGNE;

import christmas.Customer;
import christmas.menu.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GiveawayEvent {
    private static final int MINIMUM_APPLICABLE_AMOUNT = 120_000;

    private boolean isApplicable(Customer customer) {
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

    public Map<Menu, Integer> giveaway(Customer customer) {
        if (isApplicable(customer)) {
            Map<Menu, Integer> giveawayMap = new HashMap<>();
            giveawayMap.put(item(), itemCount());
            return giveawayMap;
        }
        return Collections.emptyMap();

    }
}

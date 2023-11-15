package christmas.event;

import static christmas.menu.Menu.CHAMPAGNE;

import christmas.menu.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GiveawayEvent {
    private static final int MINIMUM_APPLICABLE_AMOUNT = 120_000;
    private static final int GIVEAWAY_COUNT = 1;

    private boolean isApplicable(int totalPrice) {
        return totalPrice >= MINIMUM_APPLICABLE_AMOUNT;
    }

    public int calculateBenefitAmount(int totalPrice) {
        if (isApplicable(totalPrice)) {
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
        return GIVEAWAY_COUNT;
    }

    public Map<Menu, Integer> giveaway(int totalPrice) {
        if (isApplicable(totalPrice)) {
            Map<Menu, Integer> giveawayMap = new HashMap<>();
            giveawayMap.put(item(), itemCount());
            return giveawayMap;
        }
        return Collections.emptyMap();
    }
}

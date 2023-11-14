package christmas;

import christmas.events.ChristmasDDayDiscountEvent;
import christmas.events.DiscountEvent;
import christmas.events.GiveawayDiscountEvent;
import christmas.events.SpecialDiscountEvent;
import christmas.events.WeekdayDiscountEvent;
import christmas.events.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    private List<DiscountEvent> discountEvents;

    public EventPlanner() {
        this.discountEvents = new ArrayList<>();
        discountEvents.add(new ChristmasDDayDiscountEvent());
        discountEvents.add(new WeekdayDiscountEvent());
        discountEvents.add(new WeekendDiscountEvent());
        discountEvents.add(new SpecialDiscountEvent());
    }

    public Map<DiscountEvent, Integer> getDiscountAmounts(Customer customer) {
        Map<DiscountEvent, Integer> discountAmounts = new HashMap<>();

        for (DiscountEvent discountEvent : discountEvents) {
            if (discountEvent.isApplicable(customer)) {
                int discountAmount = discountEvent.calculateDiscount(customer);
                discountAmounts.put(discountEvent, discountAmount);
            }
        }

        return discountAmounts;
    }

    private int getTotalDisCounts(Customer customer) {
        Map<DiscountEvent, Integer> discountAmounts = getDiscountAmounts(customer);
        return discountAmounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int getTotalGiveawayPrice(Customer customer) {
        GiveawayDiscountEvent giveawayEvent = new GiveawayDiscountEvent();
        return giveawayEvent.calculateBenefitAmount(customer);
    }

    public int getTotalBenefitPrice(Customer customer) {
        return getTotalDisCounts(customer) + getTotalGiveawayPrice(customer);
    }
}

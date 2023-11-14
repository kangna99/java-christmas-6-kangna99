package christmas;

import christmas.events.Badge;
import christmas.events.ChristmasDDayDiscountEvent;
import christmas.events.DiscountEvent;
import christmas.events.GiveawayEvent;
import christmas.events.SpecialDiscountEvent;
import christmas.events.WeekdayDiscountEvent;
import christmas.events.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    public GiveawayEvent giveawayEvent;
    private List<DiscountEvent> discountEvents;
    private Customer customer;

    public EventPlanner(Customer customer) {
        this.customer = customer;
        this.discountEvents = new ArrayList<>();
        discountEvents.add(new ChristmasDDayDiscountEvent());
        discountEvents.add(new WeekdayDiscountEvent());
        discountEvents.add(new WeekendDiscountEvent());
        discountEvents.add(new SpecialDiscountEvent());
        this.giveawayEvent = new GiveawayEvent();
    }

    public Map<DiscountEvent, Integer> getDiscountAmounts() {
        Map<DiscountEvent, Integer> discountAmounts = new HashMap<>();

        for (DiscountEvent discountEvent : discountEvents) {
            if (discountEvent.isApplicable(customer)) {
                int discountAmount = discountEvent.calculateDiscount(customer);
                discountAmounts.put(discountEvent, discountAmount);
            }
        }

        return discountAmounts;
    }

    private int getTotalDisCounts() {
        Map<DiscountEvent, Integer> discountAmounts = getDiscountAmounts();
        return discountAmounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<GiveawayEvent, Integer> getTotalGiveAway() {
        Map<GiveawayEvent, Integer> giveaway = new HashMap<>();
        if (giveawayEvent.isApplicable(customer)) {
            giveaway.put(giveawayEvent, giveawayEvent.itemCount());
        }
        return giveaway;
    }

    private int getTotalGiveawayPrice() {
        return giveawayEvent.calculateBenefitAmount(customer);
    }

    public int getTotalBenefitPrice() {
        return getTotalDisCounts() + getTotalGiveawayPrice();
    }

    public int getTotalPriceAfterDiscount() {
        return customer.getOrderDetails().calculateTotalPrice() - getTotalDisCounts();
    }

    public Badge awardBadge() {
        return Badge.selectBadge(getTotalBenefitPrice());
    }
}

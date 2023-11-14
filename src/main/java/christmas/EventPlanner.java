package christmas;

import christmas.event.Badge;
import christmas.event.ChristmasDDayDiscountEvent;
import christmas.event.DiscountEvent;
import christmas.event.GiveawayEvent;
import christmas.event.SpecialDiscountEvent;
import christmas.event.WeekdayDiscountEvent;
import christmas.event.WeekendDiscountEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    public GiveawayEvent giveawayEvent;
    private List<DiscountEvent> discountEvents;
    private Customer customer;
    private int totalPrice;

    public EventPlanner(Customer customer) {
        this.customer = customer;
        this.totalPrice = customer.getOrderDetails().calculateTotalPrice();
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

    private int getTotalGiveawayPrice() {
        return giveawayEvent.calculateBenefitAmount(customer);
    }

    public int getTotalBenefitPrice() {
        return getTotalDisCounts() + getTotalGiveawayPrice();
    }

    public int getTotalPriceAfterDiscount() {
        return totalPrice - getTotalDisCounts();
    }

    public Badge awardBadge() {
        return Badge.selectBadge(getTotalBenefitPrice());
    }
}

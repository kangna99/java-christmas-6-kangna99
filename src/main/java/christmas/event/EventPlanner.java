package christmas.event;

import christmas.menu.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            if (discountEvent.isApplicable(customer.getOrderDetails().calculateTotalPrice(), customer.getVisitDate())) {
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
        return giveawayEvent.calculateBenefitAmount(customer.getOrderDetails().calculateTotalPrice());
    }

    public String getGiveaway(Customer customer) {
        Map<Menu, Integer> giveaway = giveawayEvent.giveaway(customer.getOrderDetails().calculateTotalPrice());

        if (giveaway.isEmpty()) {
            return "없음\n";
        }
        return giveaway.entrySet().stream()
                .map(entry -> String.format("%s %d개%n", entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.joining());
    }

    public int getTotalBenefitPrice() {
        return getTotalDisCounts() + getTotalGiveawayPrice();
    }

    public int getTotalPriceAfterDiscount() {
        return customer.getOrderDetails().calculateTotalPrice() - getTotalDisCounts();
    }

    public String getDiscountDetails() {
        Map<DiscountEvent, Integer> discountAmounts = getDiscountAmounts();
        return discountAmounts.entrySet().stream()
                .map(entry -> String.format("%s: -%,d원", entry.getKey().name(), entry.getValue()))
                .collect(Collectors.joining("\n", "", ""));
    }

    public String getGiveawayDetails() {
        Map<Menu, Integer> giveaway = giveawayEvent.giveaway(customer.getOrderDetails().calculateTotalPrice());
        if (giveaway.isEmpty()) {
            return "";
        }
        return String.format("%s: -%,d원\n", giveawayEvent.eventName(), giveawayEvent.item().getPrice());
    }

    public Badge awardBadge() {
        return Badge.selectBadge(getTotalBenefitPrice());
    }
}
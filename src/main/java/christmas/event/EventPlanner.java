package christmas.event;

import christmas.menu.Menu;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventPlanner {
    public final GiveawayEvent giveawayEvent;
    private final Customer customer;
    private final List<DiscountEvent> discountEvents;

    public EventPlanner(Customer customer) {
        this.customer = customer;
        this.discountEvents = List.of(
                new ChristmasDDayDiscountEvent(),
                new WeekdayDiscountEvent(),
                new WeekendDiscountEvent(),
                new SpecialDiscountEvent()
        );
        this.giveawayEvent = new GiveawayEvent();
    }

    public Map<DiscountEvent, Integer> getDiscountAmounts() {
        return discountEvents.stream()
                .filter(discountEvent -> discountEvent.isApplicable(customer.getTotalPrice(), customer.getVisitDate()))
                .collect(Collectors.toMap(
                        discountEvent -> discountEvent,
                        discountEvent -> discountEvent.calculateDiscount(customer)
                ));
    }

    private int getTotalDiscounts() {
        return getDiscountAmounts().values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int getTotalGiveawayPrice() {
        return giveawayEvent.calculateBenefitAmount(customer.getTotalPrice());
    }

    public String getGiveaway(Customer customer) {
        Map<Menu, Integer> giveaway = giveawayEvent.giveaway(customer.getTotalPrice());

        if (giveaway.isEmpty()) {
            return "없음\n";
        }
        return giveaway.entrySet().stream()
                .map(entry -> String.format("%s %d개%n", entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.joining());
    }

    public int getTotalBenefitPrice() {
        return getTotalDiscounts() + getTotalGiveawayPrice();
    }

    public int getTotalPriceAfterDiscount() {
        return customer.getTotalPrice() - getTotalDiscounts();
    }

    public String getDiscountDetails() {
        return getDiscountAmounts().entrySet().stream()
                .map(entry -> String.format("%s: -%,d원", entry.getKey().name(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }

    public String getGiveawayDetails() {
        if (getTotalGiveawayPrice() > 0) {
            return String.format("%s: -%,d원\n", giveawayEvent.eventName(), getTotalGiveawayPrice());
        }
        return "";
    }

    public Badge awardBadge() {
        return Badge.selectBadge(getTotalBenefitPrice());
    }
}
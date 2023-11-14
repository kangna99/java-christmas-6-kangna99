package christmas;

import christmas.events.ChristmasDDayEvent;
import christmas.events.Event;
import christmas.events.SpecialEvent;
import christmas.events.WeekdayEvent;
import christmas.events.WeekendEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    private List<Event> events;

    public EventPlanner() {
        this.events = new ArrayList<>();
        events.add(new ChristmasDDayEvent());
        events.add(new WeekdayEvent());
        events.add(new WeekendEvent());
        events.add(new SpecialEvent());
    }

    public Map<Event, Integer> getDiscountAmounts(Customer customer) {
        Map<Event, Integer> discountAmounts = new HashMap<>();

        for (Event event : events) {
            LocalDate visitDate = LocalDate.of(2023, 12, customer.getVisitDate());
            if (event.isApplicable(visitDate)) {
                int discountAmount = event.calculateDiscount(customer);
                discountAmounts.put(event, discountAmount);
            }
        }

        return discountAmounts;
    }

    public int getTotalDiscount(Customer customer) {
        int totalDiscount = 0;

        for (Event event : events) {
            LocalDate visitDate = LocalDate.of(2023, 12, customer.getVisitDate());
            if (event.isApplicable(visitDate)) {
                totalDiscount += event.calculateDiscount(customer);
            }
        }
        return totalDiscount;
    }
}

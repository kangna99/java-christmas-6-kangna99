package christmas.events;

import christmas.Customer;
import java.time.LocalDate;

public interface Event {
    boolean isApplicable(LocalDate date);

    int calculateDiscount(Customer customer);
}

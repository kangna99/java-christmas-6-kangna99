package christmas.controller;

import christmas.event.Customer;
import christmas.event.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();

        int visitDate = inputView.readDate();
        Customer customer = new Customer(visitDate, inputView.readOrder());
        EventPlanner eventPlanner = new EventPlanner(customer);

        outputView.printPreviewDetails(visitDate);
        outputView.printOrder(customer.getOrderDetails());
        outputView.printTotalPriceBeforeDiscount(customer.getOrderDetails());
        outputView.printGiveAwayMenu(eventPlanner.getGiveaway(customer));
        outputView.printBenefitDetails(eventPlanner.getDiscountDetails(), eventPlanner.getGiveawayDetails());
        outputView.printTotalBenefitPrice(eventPlanner.getTotalBenefitPrice());
        outputView.printTotalPriceAfterDiscount(eventPlanner.getTotalPriceAfterDiscount());
        outputView.printDecemberEventBadge(eventPlanner.awardBadge());
    }
}
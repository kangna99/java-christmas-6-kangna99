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

        Customer customer = new Customer(inputView.readDate(), inputView.readOrder());
        EventPlanner eventPlanner = new EventPlanner(customer);

        outputView.printPreviewDetails(customer.getVisitDate());
        outputView.printOrderDetails(customer.getOrderDetails());
        outputView.printTotalPriceBeforeDiscount(customer.getTotalPrice());
        outputView.printGiveAwayMenu(eventPlanner.getGiveaway(customer));
        outputView.printBenefitDetails(eventPlanner.getDiscountDetails(), eventPlanner.getGiveawayDetails());
        outputView.printTotalBenefitPrice(eventPlanner.getTotalBenefitPrice());
        outputView.printTotalPriceAfterDiscount(eventPlanner.getTotalPriceAfterDiscount());
        outputView.printDecemberEventBadge(eventPlanner.awardBadge());
    }
}
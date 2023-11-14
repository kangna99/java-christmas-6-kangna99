package christmas;

import static christmas.constants.GuideMessage.START_MESSAGE;

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
        System.out.println(START_MESSAGE);
        int date = inputView.readDate();
        OrderDetails orderDetails = inputView.readOrder();
        outputView.printOrder(orderDetails);
        outputView.printTotalPrice(orderDetails);
    }
}

package christmas.controller;

import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import christmas.model.OrderList;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {
    private OrderList orderList;
    private OrderAmount orderAmount;
    private OrderDate orderDate;

    public OrderController() {}

    public void proceedOrder() {
        requestDate();
    }

    private void requestDate() {
        while(true) {
            try {
                String inputNumber = InputView.readDate();
            } catch () {

            }
        }
    }
}

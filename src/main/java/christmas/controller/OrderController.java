package christmas.controller;

import christmas.model.MenuItem;
import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import christmas.model.OrderList;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Collections;
import java.util.List;

public class OrderController {
    private OrderList orderList;
    private OrderAmount orderAmount;
    private OrderDate orderDate;

    public OrderController() {}

    public void proceedOrder() {
        requestDate();
        requestMenu();
        generateOrderAmount();
    }

    private void requestDate() {
        while (true) {
            try {
                Integer date = InputView.readDate();

                orderDate = OrderDate.from(date);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void requestMenu() {
        while (true) {
            try {
                List<MenuItem> menuList = InputView.readMenu();

                orderList = OrderList.from(menuList);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void generateOrderAmount() {
        orderAmount = OrderAmount.from(orderList.getAmount());
    }

    public void showResultOrderList() {
        OutputView.printOrderList(orderList.toString());
    }

    public void showResultSubTotal() {
        OutputView.printTotalAmountBeforeDiscount(orderAmount.toString());
    }

    public OrderDate getOrderDate() {
        return orderDate;
    }

    public OrderAmount getOrderAmount() {
        return orderAmount;
    }

    public OrderList getOrderList() {
        return orderList;
    }
}

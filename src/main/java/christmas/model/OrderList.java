package christmas.model;

import christmas.constants.OutputMessage;
import java.util.List;

public class OrderList {
    private final List<MenuItem> orderList;

    private OrderList(List<MenuItem> orderList) {
        this.orderList = orderList;
    }

    public static OrderList from(List<MenuItem> orderList) {
        // validate
        return new OrderList(orderList);
    }

    @Override
    public String toString() {
        return String.join(
                OutputMessage.LINE_FEED,
                orderList.stream()
                        .map(MenuItem::toString)
                        .toList()
        );
    }
}

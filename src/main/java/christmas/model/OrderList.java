package christmas.model;

import christmas.constants.OutputMessage;
import christmas.constants.enums.ErrorMessage;
import christmas.util.Validator;
import java.util.List;

public class OrderList {
    private final List<MenuItem> orderList;

    private OrderList(List<MenuItem> orderList) {
        this.orderList = orderList;
    }

    private static void validate(List<MenuItem> orderList) {
        Validator.validateNumberLessThanCriteria(
                20,
                orderList.stream()
                        .mapToInt(MenuItem::getCount)
                        .sum(),
                ErrorMessage.INVALID_ORDER.getMessage()
        );

        Validator.validateDuplicate(
                orderList.stream()
                        .map(MenuItem::getName)
                        .toList(),
                ErrorMessage.INVALID_ORDER.getMessage()
        );
    }

    public static OrderList from(List<MenuItem> orderList) {
        validate(orderList);

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

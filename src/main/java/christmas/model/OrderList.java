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
        Integer countMenu = orderList.stream()
                .mapToInt(MenuItem::getCount)
                .sum();

        Validator.validateDuplicate(
            orderList.stream()
                    .map(MenuItem::getName)
                    .toList(),
            ErrorMessage.INVALID_ORDER.getMessage()
        );

        if (countMenu == 1) {
            Validator.validateSingleMenuItem(orderList.get(0), ErrorMessage.INVALID_ORDER.getMessage());
        }

        Validator.validateNumberInRange(
                1,
                20,
                countMenu,
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

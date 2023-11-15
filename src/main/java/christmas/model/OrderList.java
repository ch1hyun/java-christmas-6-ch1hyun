package christmas.model;

import christmas.constants.EventConstant;
import christmas.constants.OutputMessage;
import christmas.constants.enums.ErrorMessage;
import christmas.constants.enums.MenuBoard;
import christmas.util.Converter;
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

        if (countMenu == EventConstant.COUNT_LOWER) {
            Validator.validateSingleMenuItemIsBeverage(orderList.get(EventConstant.FIRST_ITEM), ErrorMessage.INVALID_ORDER.getMessage());
        }

        Validator.validateNumberInRange(
                EventConstant.COUNT_LOWER,
                EventConstant.COUNT_UPPER,
                countMenu,
                ErrorMessage.INVALID_ORDER.getMessage()
        );
    }

    public static OrderList from(List<MenuItem> orderList) {
        validate(orderList);

        return new OrderList(orderList);
    }

    public Integer getAmount() {
        return orderList.stream()
                .mapToInt(MenuItem::getAmount)
                .sum();
    }

    public Integer getCountMatchMenuGroup(MenuBoard menuBoard) {
        return orderList.stream()
                .filter(menuItem -> menuItem.isGroup(menuBoard))
                .mapToInt(MenuItem::getCount)
                .sum();
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

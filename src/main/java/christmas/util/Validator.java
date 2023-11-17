package christmas.util;

import christmas.constants.enums.Menu;
import christmas.constants.enums.MenuBoard;
import christmas.model.MenuItem;
import java.util.List;

public class Validator {
    private Validator() {}

    public static void validatePositiveNumber(Integer number, String errorMessage) {
        if (number <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateNumberInRange(Integer from, Integer to, Integer number, String errorMessage) {
        if (number < from || to < number) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateOnlyContainBeverage(List<MenuItem> menuItemList, String errorMessage) {
        Long size = menuItemList.stream()
                .filter(menuItem -> menuItem.isGroup(MenuBoard.음료))
                .count();

        if (menuItemList.size() == size.intValue()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static<MenuItem> void validateDuplicate(List<String> menuItemList, String errorMessage) {
        if (menuItemList.size() != menuItemList.stream().distinct().count()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

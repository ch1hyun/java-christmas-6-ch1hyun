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

    public static void validateSingleMenuItem(MenuItem menuItem, String errorMessage) {
        if (MenuBoard.음료.contains(
                Menu.valueOf(menuItem.getName())
        )) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static<T> void validateDuplicate(List<T> list, String errorMessage) {
        if (list.size() != list.stream().distinct().count()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

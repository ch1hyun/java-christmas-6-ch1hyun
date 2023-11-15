package christmas.util;

import christmas.constants.EventConstant;
import christmas.model.MenuItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Converter {
    private static final Long ZERO_TYPE_LONG = 0L;
    private static final Integer FIRST_ITEM = 0;
    private static final Integer SECOND_ITEM = 1;

    private Converter() {}

    public static Integer parseStringToInteger(String number) {
            return Integer.parseInt(number);
    }

    public static List<MenuItem> parseStringToMenuList(String menu) {
        List<String> order = Arrays.stream(menu.split(EventConstant.DELIMITER)).toList();

        List<MenuItem> result = new ArrayList<>();

        for (String orderItem : order) {
            String[] splited = orderItem.split(EventConstant.CONNECTOR);
            String menuName = splited[FIRST_ITEM];
            Integer count = parseStringToInteger(splited[SECOND_ITEM]);

            result.add(MenuItem.of(menuName, count));
        }

        return result;
    }
}

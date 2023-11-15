package christmas.util;

import christmas.model.MenuItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Generator {
    private Generator() {}

    public static Integer parseStringToInteger(String number) {
            return Integer.parseInt(number);
    }

    public static Integer parseLongToInteger(Long number) {
        return Long.valueOf(
                Optional.ofNullable(number).orElse(0L)
        ).intValue();
    }

    public static List<MenuItem> parseStringToMenuList(String menu) {
        List<String> order = Arrays.stream(menu.split(",")).toList();

        List<MenuItem> result = new ArrayList<>();

        for (String orderItem : order) {
            String[] splited = orderItem.split("-");
            String menuName = splited[0];
            Integer count = parseStringToInteger(splited[1]);

            result.add(MenuItem.of(menuName, count));
        }

        return result;
    }
}

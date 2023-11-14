package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.InputMessage;
import christmas.constants.enums.ErrorMessage;
import christmas.model.MenuItem;
import christmas.util.Generator;
import java.util.List;

public class InputView {
    private InputView() {}

    public static Integer readDate() {
        System.out.println(InputMessage.DATE);

        try {
            return Generator.parseStringToInteger(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public static List<MenuItem> readMenu() {
        System.out.println(InputMessage.ORDER_MENU);

        try {
            return Generator.parseStringToMenuList(Console.readLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}

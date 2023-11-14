package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.InputMessage;
import christmas.model.MenuItem;
import java.util.List;

public class InputView {
    private InputView() {}

    public static Integer readDate() {
        System.out.println(InputMessage.DATE);

        return Generator.parseStringToInteger(Console.readLine());
    }

    public static List<MenuItem> readMenu() {
        System.out.println(InputMessage.ORDER_MENU);

        return Generator.parseStringToMenuList(Console.readLine());
    }
}

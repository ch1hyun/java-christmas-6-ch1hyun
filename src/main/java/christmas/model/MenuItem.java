package christmas.model;

import christmas.constants.enums.Menu;
import christmas.constants.enums.MenuBoard;
import christmas.util.Formatter;

public class MenuItem {
    private final Menu menu;
    private final Count count;

    private MenuItem(Menu menu, Count count) {
        this.menu = menu;
        this.count = count;
    }

    public static MenuItem of(String menuName, Integer count) {
        return new MenuItem(Menu.valueOf(menuName), Count.from(count));
    }

    public Boolean isGroup(MenuBoard menuBoard) {
        return menuBoard.contains(menu);
    }

    public Integer getAmount() {
        return menu.getAmount() * count.getCount();
    }

    public Integer getCount() {
        return count.getCount();
    }

    @Override
    public String toString() {
        return Formatter.formatOrderItem(menu.getName(), count.getCount());
    }
}

package christmas.constants.enums;

import java.util.Arrays;
import java.util.List;

public enum MenuBoard {
    애피타이저("애피타이저", Arrays.asList(Menu.양송이수프, Menu.타파스, Menu.시저샐러드)),
    메인("메인", Arrays.asList(Menu.티본스테이크, Menu.바비큐립, Menu.해산물파스타, Menu.크리스마스파스타)),
    디저트("디저트", Arrays.asList(Menu.초코케이크, Menu.아이스크림)),
    음료("음료", Arrays.asList(Menu.제로콜라, Menu.레드와인, Menu.샴페인));

    private final String category;
    private final List<Menu> menuList;

    MenuBoard(String category, List<Menu> menuList) {
        this.category = category;
        this.menuList = menuList;
    }

    public Boolean contains(Menu menu) {
        return menuList.contains(menu);
    }
}

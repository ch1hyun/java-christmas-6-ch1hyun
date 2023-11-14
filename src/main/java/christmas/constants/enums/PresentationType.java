package christmas.constants.enums;

import christmas.model.OrderAmount;

public enum PresentationType {
    NULL(Menu.NULL, 0),
    샴페인(Menu.샴페인, 120_000);

    private final Menu menu;
    private final Integer minimumAmount;
    private static final String name = "증정 이벤트";

    PresentationType(Menu menu, Integer minimumAmount) {
        this.menu = menu;
        this.minimumAmount = minimumAmount;
    }

    public static PresentationType getPresentationType(OrderAmount orderAmount) {
        if (orderAmount.isGreaterThanOrEqualTo(PresentationType.샴페인.minimumAmount)) {
            return PresentationType.샴페인;
        }

        return PresentationType.NULL;
    }

    public String getName() {
        return name;
    }
    public Integer getAmount() {
        return menu.getAmount();
    }
}

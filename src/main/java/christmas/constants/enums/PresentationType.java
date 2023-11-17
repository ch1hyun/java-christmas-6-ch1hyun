package christmas.constants.enums;

import christmas.model.OrderAmount;

public enum PresentationType {
    NULL(Menu.NULL, 0),
    샴페인(Menu.샴페인, 120_000);

    private final Menu menu;
    private final Integer minimumAmount;

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

    public Integer getAmount() {
        return menu.getAmount();
    }

    public String getName() {
        return menu.getName();
    }

    public Integer getCount() {
        return 1;
    }
}

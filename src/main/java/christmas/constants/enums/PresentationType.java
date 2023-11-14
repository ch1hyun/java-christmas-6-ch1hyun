package christmas.constants.enums;

public enum PresentationType {
    NULL(Menu.NULL, 0),
    샴페인(Menu.샴페인, 120_000);

    private final Menu menu;
    private final Integer minimumAmount;

    PresentationType(Menu menu, Integer minimumAmount) {
        this.menu = menu;
        this.minimumAmount = minimumAmount;
    }

    public PresentationType getPresentationType(Integer subTotal) {
        if (subTotal >= PresentationType.샴페인.minimumAmount) {
            return PresentationType.샴페인;
        }

        return PresentationType.NULL;
    }
}

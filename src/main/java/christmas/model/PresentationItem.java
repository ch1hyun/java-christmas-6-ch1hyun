package christmas.model;

import christmas.constants.enums.PresentationType;
import christmas.util.Formatter;

public class PresentationItem {
    private final PresentationType presentationType;

    private PresentationItem(PresentationType presentationType) {
        this.presentationType = presentationType;
    }

    public static PresentationItem from(OrderAmount orderAmount) {
        return new PresentationItem(PresentationType.getPresentationType(orderAmount));
    }

    public Integer getAmount() {
        return presentationType.getAmount();
    }

    public Boolean isEmpty() {
        return presentationType == PresentationType.NULL;
    }

    public void printMenu() {
        // output view 연결
    }

    @Override
    public String toString() {
        return Formatter.formatRewardItem(
                presentationType.getName(),
                presentationType.getAmount()
        );
    }
}

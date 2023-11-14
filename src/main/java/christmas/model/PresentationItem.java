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
        return presentationType.getAmount() * presentationType.getCount();
    }

    public Boolean isEmpty() {
        return presentationType == PresentationType.NULL;
    }

    @Override
    public String toString() {
        return Formatter.formatOrderItem(
                presentationType.getName(),
                presentationType.getCount()
        );
    }
}

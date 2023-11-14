package christmas.model.event;

import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.constants.enums.PresentationType;
import christmas.model.OrderAmount;
import christmas.model.PresentationItem;
import christmas.util.Formatter;

public class PresentationEvent {
    private static final String EVENT_NAME = "증정 이벤트";
    private final Activation activation;
    private final PresentationItem presentationItem;

    private PresentationEvent(Activation activation, PresentationItem presentationItem) {
        this.activation = activation;
        this.presentationItem = presentationItem;
    }

    public static PresentationEvent from(OrderAmount orderAmount) {
        PresentationItem presentationItem = PresentationItem.from(orderAmount);
        Boolean active = true;

        if (presentationItem.isEmpty()) {
            active = false;
        }

        return new PresentationEvent(Activation.from(active), presentationItem);
    }

    public PresentationItem getPresentationItem() {
        return presentationItem;
    }

    public Boolean isAcitve() {
        return activation.isActive();
    }

    public String toString() {
        if (isAcitve()) {
            return Formatter.formatRewardItem(
                    EVENT_NAME,
                    -presentationItem.getAmount()
            );
        }

        return "";
    }
}

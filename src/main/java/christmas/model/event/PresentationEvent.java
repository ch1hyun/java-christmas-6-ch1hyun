package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.OutputMessage;
import christmas.constants.enums.Day;
import christmas.constants.enums.DiscountType;
import christmas.constants.enums.PresentationType;
import christmas.model.OrderAmount;
import christmas.model.PresentationItem;
import christmas.util.Formatter;

public class PresentationEvent {
    private final Activation activation;
    private final PresentationItem presentationItem;

    private PresentationEvent(Activation activation, PresentationItem presentationItem) {
        this.activation = activation;
        this.presentationItem = presentationItem;
    }

    public static PresentationEvent from(OrderAmount orderAmount) {
        PresentationItem presentationItem = PresentationItem.from(orderAmount);
        Boolean active = EventConstant.TRUE;

        if (presentationItem.isEmpty()) {
            active = EventConstant.FALSE;
        }

        return new PresentationEvent(Activation.from(active), presentationItem);
    }

    public PresentationItem getPresentationItem() {
        return presentationItem;
    }

    public String getMenu() {
        return presentationItem.toString();
    }

    public Boolean isAcitve() {
        return activation.isActive();
    }

    public String toString() {
        if (isAcitve()) {
            return Formatter.formatRewardItem(
                    EventConstant.PRESENTATION_EVENT_NAME,
                    -presentationItem.getAmount()
            );
        }

        return OutputMessage.EMPTY;
    }
}

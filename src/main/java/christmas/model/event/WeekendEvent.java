package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.OutputMessage;
import christmas.constants.enums.DiscountType;
import christmas.constants.enums.MenuBoard;
import christmas.model.OrderDate;
import christmas.util.Formatter;

public class WeekendEvent {
    private final Activation activation;
    private final DiscountType discountType;
    private final MenuBoard menuBoard;

    private WeekendEvent(Activation activation) {
        this.activation = activation;
        this.discountType = DiscountType.주말;
        this.menuBoard = MenuBoard.메인;
    }

    public static WeekendEvent from(OrderDate orderDate) {
        Boolean active = EventConstant.FALSE;

        if (orderDate.isDayGroup(DiscountType.주말)) {
            active = EventConstant.TRUE;
        }

        return new WeekendEvent(Activation.from(active));
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
    public MenuBoard getMenuBoard() {
        return menuBoard;
    }

    public Boolean isAcitve() {
        return activation.isActive();
    }

    public String toString(Integer count) {
        if (isAcitve() && count > EventConstant.ZERO) {
            return Formatter.formatRewardItem(
                    EventConstant.WEEKEND_DISCOUNT_NAME,
                    -discountType.getAmount(count)
            );
        }

        return OutputMessage.EMPTY;
    }
}

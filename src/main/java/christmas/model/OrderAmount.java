package christmas.model;

import christmas.util.Formatter;

public class OrderAmount {
    private final Integer subTotal;

    private OrderAmount(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public static OrderAmount from(Integer subTotal) {
        return new OrderAmount(subTotal);
    }

    public Boolean isGreaterThanOrEqualTo(Integer amount) {
        return subTotal >= amount;
    }

    public String getTotalAmount(RewardAmount rewardAmount) {
        return Formatter.formatAmount(
                subTotal - rewardAmount.getDiscountAmount()
        );
    }

    @Override
    public String toString() {
        return Formatter.formatAmount(subTotal);
    }
}

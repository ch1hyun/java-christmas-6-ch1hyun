package christmas.model;

import christmas.util.Formatter;

public class RewardAmount {
    private final Integer discountAmount;
    private final Integer presentationAmount;

    private RewardAmount(Integer discountAmount, Integer presentationAmount) {
        this.discountAmount = discountAmount;
        this.presentationAmount = presentationAmount;
    }

    public static RewardAmount of(DiscountList discountList, PresentationItem presentationItem) {
        return new RewardAmount(discountList.getAmount(), presentationItem.getAmount());
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public Boolean isGreaterThanOrEqualTo(Integer amount) {
        return discountAmount + presentationAmount >= amount;
    }

    @Override
    public String toString() {
        return Formatter.formatAmount(-(discountAmount + presentationAmount));
    }
}

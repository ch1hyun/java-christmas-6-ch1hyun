package christmas.model;

import christmas.constants.OutputMessage;
import java.util.List;

public class DiscountList {
    private final List<DiscountItem> discountList;

    private DiscountList(List<DiscountItem> discountList) {
        this.discountList = discountList;
    }

    public static DiscountList from(List<DiscountItem> discountList) {
        return new DiscountList(discountList);
    }

    public Boolean isEmpty() {
        return discountList.isEmpty();
    }

    public Integer getAmount() {
        return discountList.stream()
                .mapToInt(DiscountItem::getAmount)
                .sum();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return OutputMessage.NOTHING;
        }
        
        return String.join(
                OutputMessage.LINE_FEED,
                discountList.stream()
                        .map(DiscountItem::toString)
                        .toList()
        );
    }
}

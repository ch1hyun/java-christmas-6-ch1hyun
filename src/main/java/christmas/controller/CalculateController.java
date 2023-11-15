package christmas.controller;

import christmas.model.OrderAmount;
import christmas.model.RewardAmount;
import christmas.view.OutputView;

public class CalculateController {
    private OrderAmount orderAmount;
    private RewardAmount rewardAmount;

    public CalculateController() {}

    public void proceedCalculate(OrderAmount orderAmount, RewardAmount rewardAmount) {
        this.orderAmount = orderAmount;
        this.rewardAmount = rewardAmount;
    }

    public void showResultTotal() {
        OutputView.printTotalAmountAfterDiscount(orderAmount.getTotalAmount(rewardAmount));
    }
}

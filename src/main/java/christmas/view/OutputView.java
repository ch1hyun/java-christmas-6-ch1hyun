package christmas.view;

import christmas.constants.OutputMessage;

public class OutputView {
    private OutputView() {}

    private static void printLineFeed() {
        System.out.println();
    }

    public static void printStartSystem() {
        System.out.println(OutputMessage.START_SYSTEM);
    }

    public static void printStartResult() {
        System.out.println(OutputMessage.START_RESULT);
        printLineFeed();
    }

    public static void printOrderList(String orderList) {
        System.out.println(OutputMessage.TITLE_ORDER_LIST);
        System.out.println(orderList);
        printLineFeed();
    }

    public static void printTotalAmountBeforeDiscount(String amount) {
        System.out.println(OutputMessage.TITLE_TOTAL_AMOUNT_BEFORE_DISCOUNT);
        System.out.println(amount);
        printLineFeed();
    }

    public static void printPresentationItem(String menu) {
        System.out.println(OutputMessage.TITLE_PRESENTATION_ITEM);
        System.out.println(menu);
        printLineFeed();
    }

    public static void printRewardList(String rewardList) {
        System.out.println(OutputMessage.TITLE_REWARD_LIST);
        System.out.println(rewardList);
        printLineFeed();
    }

    public static void printRewardAmount(String amount) {
        System.out.println(OutputMessage.TITLE_REWARD_AMOUNT);
        System.out.println(amount);
        printLineFeed();
    }

    public static void printTotalAmountAfterDiscount(String amount) {
        System.out.println(OutputMessage.TITLE_TOTAL_AMOUNT_AFTER_DISCOUNT);
        System.out.println(amount);
        printLineFeed();
    }

    public static void printBadge(String badge) {
        System.out.println(OutputMessage.TITLE_EVENT_BADGE);
        System.out.println(badge);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        printLineFeed();
    }
}

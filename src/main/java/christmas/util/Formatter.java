package christmas.util;

import christmas.constants.FormatMessage;
import java.text.DecimalFormat;

public class Formatter {
    private Formatter() {}

    private static final DecimalFormat decimalFormat = new DecimalFormat(FormatMessage.DECIMAL_AMOUNT);

    public static String formatAmount(Integer amount) {
        return String.format(
                FormatMessage.AMOUNT,
                decimalFormat.format(amount)
        );
    }

    public static String formatOrderItem(String item, Integer count) {
        return String.format(
                FormatMessage.ORDER_ITEM,
                item,
                count
        );
    }

    public static String formatRewardItem(String item, Integer amount) {
        return String.format(
                FormatMessage.REWARD_ITEM,
                item,
                decimalFormat.format(amount)
        );
    }

    public static String formatError(String message) {
        return String.format(
                FormatMessage.ERROR,
                message
        );
    }
}

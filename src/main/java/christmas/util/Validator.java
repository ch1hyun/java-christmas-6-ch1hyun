package christmas.util;

public class Validator {
    private Validator() {}

    public static void validatePositiveNumber(Integer number, String errorMessage) {
        if (number <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

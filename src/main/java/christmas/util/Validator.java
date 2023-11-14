package christmas.util;

public class Validator {
    private Validator() {}

    public static void validatePositiveNumber(Integer number, String errorMessage) {
        if (number <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateNumberInRange(Integer number, String errorMessage) {
        if (number < 1 || 31 < number) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

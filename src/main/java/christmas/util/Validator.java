package christmas.util;

public class Validator {
    private Validator() {}

    public static void validatePositiveNumber(Integer number, String errorMessage) {
        if (number <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateNumberInRange(Integer from, Integer to, Integer number, String errorMessage) {
        if (number < from || to < number) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateNumberLessThanCriteria(Integer criteria, Integer number, String errorMessage) {
        if (number > criteria) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}

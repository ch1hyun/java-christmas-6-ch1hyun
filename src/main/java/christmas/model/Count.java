package christmas.model;

public class Count {
    private final Integer count;

    private Count(Integer count) {
        // validate
        this.count = count;
    }

    public static Count from(Integer count) {
        return new Count(count);
    }

    public Integer getCount() {
        return count;
    }
}

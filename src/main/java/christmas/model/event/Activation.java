package christmas.model.event;

public class Activation {
    private final Boolean active;

    private Activation(Boolean active) {
        this.active = active;
    }

    public static Activation from(Boolean active) {
        return new Activation(active);
    }

    public Boolean isActive() {
        return active;
    }
}

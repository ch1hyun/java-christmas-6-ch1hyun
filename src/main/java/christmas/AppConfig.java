package christmas;

import christmas.controller.CalculateController;
import christmas.controller.EntryController;
import christmas.controller.EventController;
import christmas.controller.EventPlannerController;
import christmas.controller.OrderController;

public class AppConfig {
    private static AppConfig appConfig;
    private static EntryController entryController;
    private static OrderController orderController;
    private static EventController eventController;
    private static CalculateController calculateController;

    private AppConfig() {
        entryController = EventPlannerController.of(
                getOrderControllerInstance(),
                getEventControllerInstance(),
                getCalculateControllerInstance()
        );
    }

    public static AppConfig getInstance() {
        if (appConfig == null) {
            appConfig = new AppConfig();
        }

        return appConfig;
    }

    public EntryController getEntryControllerInstance() {
        return entryController;
    }

    private OrderController getOrderControllerInstance() {
        if (orderController == null) {
            orderController = new OrderController();
        }

        return orderController;
    }

    private EventController getEventControllerInstance() {
        if (eventController == null) {
            eventController = new EventController();
        }

        return eventController;
    }

    private CalculateController getCalculateControllerInstance() {
        if (calculateController == null) {
            calculateController = new CalculateController();
        }

        return calculateController;
    }
}

package christmas;

import christmas.controller.EntryController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();

        EntryController entryController = appConfig.getEntryControllerInstance();;
        entryController.proceed();
    }
}

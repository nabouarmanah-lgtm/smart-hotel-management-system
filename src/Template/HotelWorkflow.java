package Template;

import Observer.NotificationService;

public abstract class HotelWorkflow {
    protected NotificationService notificationService;

    public HotelWorkflow(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public final void execute() {
        authenticateGuest();
        prepareRoom();
        performAction();
        sendNotification();
        finish();
    }

    protected void authenticateGuest() {
        System.out.println("Authenticating guest...");
    }

    protected void prepareRoom() {
        System.out.println("Preparing room...");
    }

    protected abstract void performAction();
    protected abstract void sendNotification();

    protected void finish() {
        System.out.println("Workflow completed.");
    }
}
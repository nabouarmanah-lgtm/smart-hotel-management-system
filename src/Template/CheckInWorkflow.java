package Template;

import Observer.NotificationEvent;
import Observer.NotificationService;
import Observer.NotificationType;

public class CheckInWorkflow extends HotelWorkflow {
    public CheckInWorkflow(NotificationService notificationService) {
        super(notificationService);
    }

    @Override
    protected void performAction() {
        System.out.println("Assigning room to guest...");
    }

    @Override
    protected void sendNotification() {
        notificationService.notifyObservers(
            new NotificationEvent(NotificationType.CHECK_IN_COMPLETED, "Guest has successfully checked in.")
        );
    }
}
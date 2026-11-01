package Template;

import Observer.NotificationEvent;
import Observer.NotificationService;
import Observer.NotificationType;

public class CheckOutWorkflow extends HotelWorkflow {
    public CheckOutWorkflow(NotificationService notificationService) {
        super(notificationService);
    }

    @Override
    protected void performAction() {
        System.out.println("Calculating bill and releasing room...");
    }

    @Override
    protected void sendNotification() {
        notificationService.notifyObservers(
            new NotificationEvent(NotificationType.CHECK_OUT_COMPLETED, "Guest has successfully checked out.")
        );
    }
}
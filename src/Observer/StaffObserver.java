package Observer;

public class StaffObserver implements Observer {
    @Override
    public void update(NotificationEvent event) {
        System.out.println("[STAFF] " + event.getType() + " -> " + event.getMessage());
    }
}
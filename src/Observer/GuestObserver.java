package Observer;

public class GuestObserver implements Observer {
    @Override
    public void update(NotificationEvent event) {
        System.out.println("[GUEST] " + event.getType() + " -> " + event.getMessage());
    }
}
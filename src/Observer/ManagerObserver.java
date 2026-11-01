package Observer;

public class ManagerObserver implements Observer {
    @Override
    public void update(NotificationEvent event) {
        System.out.println("[MANAGER] " + event.getType() + " -> " + event.getMessage());
    }
}
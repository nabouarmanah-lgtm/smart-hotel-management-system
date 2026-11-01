package app;

import Adapter.LegacyPaymentProcessor;
import Adapter.PaymentAdapter;
import Adapter.PaymentProcessor;
import Observer.GuestObserver;
import Observer.ManagerObserver;
import Observer.NotificationEvent;
import Observer.NotificationService;
import Observer.NotificationType;
import Observer.StaffObserver;
import Singltone.HotelConfig;
import Strategy.BillingContext;
import Strategy.MemberDiscount;
import Strategy.RegularPricing;
import Strategy.SeasonalPricing;
import Template.CheckInWorkflow;
import Template.CheckOutWorkflow;
import Template.HotelWorkflow;
import booking.Booking;
import com.mycompany.finalp.Factories.SuiteRoomFactory;
import com.mycompany.finalp.Factories.RoomFactory;
import com.mycompany.finalp.Room;

public class FinalP {
    public static void main(String[] args) {

        // ================================
        // 1. SINGLETON - Hotel Config
        // ================================
        HotelConfig config = HotelConfig.getInstance();
        System.out.println("Welcome to: " + config.getHotelname());
        System.out.println("------------------------------------");

        // ================================
        // 2. FACTORY METHOD - Room Creation
        // ================================
        RoomFactory fact = new SuiteRoomFactory();
        Room myroom = fact.createRoom();
        System.out.println("Room Created:");
        System.out.println(myroom.roomInfo());
        System.out.println("------------------------------------");

        // ================================
        // 3. BUILDER - Booking
        // ================================
        Booking bo = new Booking.Builder()
                .setRoom(myroom)
                .setName("Nana")
                .setBreakfast(true)
                .setNights(3)
                .setroomType("Suite Room")
                .setAirportpickup(true)
                .build();
        System.out.println("Booking Confirmation Details:");
        bo.displayInfo();
        System.out.println("------------------------------------");

        // ================================
        // 4. OBSERVER - Notifications
        // ================================
        NotificationService notificationService = new NotificationService();
        notificationService.addObserver(new GuestObserver());
        notificationService.addObserver(new StaffObserver());
        notificationService.addObserver(new ManagerObserver());

        notificationService.notifyObservers(
            new NotificationEvent(NotificationType.BOOKING_CONFIRMED,
                "Your booking has been confirmed successfully!")
        );
        System.out.println("------------------------------------");

        // ================================
        // 5. TEMPLATE METHOD - Check In
        // ================================
        System.out.println(">> Check-In Workflow:");
        HotelWorkflow checkIn = new CheckInWorkflow(notificationService);
        checkIn.execute();
        System.out.println("------------------------------------");

        // ================================
        // 6. STRATEGY - Billing
        // ================================
        System.out.println(">> Billing:");
        BillingContext billing = new BillingContext(new RegularPricing());
        double total = billing.executeStrategy(myroom.getPrice(), 3);
        System.out.println("Total (Regular): $" + total);

        billing.setStrategy(new MemberDiscount());
        total = billing.executeStrategy(myroom.getPrice(), 3);
        System.out.println("Total (Member Discount): $" + total);

        billing.setStrategy(new SeasonalPricing());
        total = billing.executeStrategy(myroom.getPrice(), 3);
        System.out.println("Total (Seasonal): $" + total);
        System.out.println("------------------------------------");

        // ================================
        // 7. ADAPTER - Legacy Payment
        // ================================
        System.out.println(">> Payment Processing:");
        LegacyPaymentProcessor legacy = new LegacyPaymentProcessor();
        PaymentProcessor payment = new PaymentAdapter(legacy, "1234-5678-9012-3456");
        payment.processPayment(total);
        System.out.println("------------------------------------");

        // ================================
        // 8. TEMPLATE METHOD - Check Out
        // ================================
        System.out.println(">> Check-Out Workflow:");
        HotelWorkflow checkOut = new CheckOutWorkflow(notificationService);
        checkOut.execute();
        System.out.println("------------------------------------");

        System.out.println("System process completed successfully!");
    }
}
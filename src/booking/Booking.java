package booking;

import com.mycompany.finalp.Room;

public class Booking {
    private Room room;
    private String name;
    private boolean breakfast;
    private int nights;
    private boolean airportpickup;
    private String roomType;

      private Booking(Builder b) {
        this.room = b.room;
        this.name = b.name;
        this.breakfast = b.breakfast;
        this.nights = b.nights;
        this.airportpickup = b.airportpickup;
        this.roomType = b.roomType;
    }

    public void displayInfo() {
        System.out.println("GuestName: " + name);
        System.out.println("Room Type: " + roomType);
        System.out.println("Price: " + room.getPrice());
        System.out.println("Nights Booked: " + nights);
        System.out.println("Breakfast included: " + (breakfast ? "Yes" : "No"));
        System.out.println("AirPortPickup: " + (airportpickup ? "Yes" : "No"));
        System.out.println("---------------------------");
        System.out.println("THANK YOU FOR CHOOSING US :)");
    }

       public static class Builder {
        private Room room;
        private String name;
        private boolean breakfast;
        private int nights;
        private boolean airportpickup;
        private String roomType;

        public Builder setRoom(Room room) { this.room = room; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setBreakfast(boolean b) { this.breakfast = b; return this; }
        public Builder setNights(int n) { this.nights = n; return this; }
        public Builder setAirportpickup(boolean a) { this.airportpickup = a; return this; }
        public Builder setroomType(String t) { this.roomType = t; return this; }
        public Booking build() { return new Booking(this); }
    }
}
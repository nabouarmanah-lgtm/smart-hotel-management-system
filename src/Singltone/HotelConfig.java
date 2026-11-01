package Singltone;

public class HotelConfig {
    private static HotelConfig instance;
    private String hotelname = "Grand SHMS Hotel";

    private HotelConfig() {}

    public static HotelConfig getInstance() {
        if (instance == null) {
            instance = new HotelConfig();
        }
        return instance;
    }

    public String getHotelname() { return hotelname; }
}
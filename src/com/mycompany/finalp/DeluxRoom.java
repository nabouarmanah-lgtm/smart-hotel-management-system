package com.mycompany.finalp;

public class DeluxRoom implements Room {
    private double price = 250;
    private int capacity = 2;
    private String features = "Deluxe Room with sea view and mini bar";

    @Override
    public double getPrice() { return price; }

    @Override
    public String roomInfo() {
        return "Features: " + features + "\nPrice: " + price + "\nCapacity: " + capacity;
    }
}
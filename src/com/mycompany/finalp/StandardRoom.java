package com.mycompany.finalp;

public class StandardRoom implements Room {
    private double price = 100;
    private int capacity = 2;
    private String features = "Standard Room with basic amenities";

    @Override
    public double getPrice() { return price; }

    @Override
    public String roomInfo() {
        return "Features: " + features + "\nPrice: " + price + "\nCapacity: " + capacity;
    }
}
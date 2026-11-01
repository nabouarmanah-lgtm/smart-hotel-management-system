package com.mycompany.finalp;

public class SuiteRoom implements Room {
    private double price = 600;
    private int capacity = 3;
    private String features = "Suite Room with King-size bed and living area";

    @Override
    public double getPrice() { return price; }

    @Override
    public String roomInfo() {
        return "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\nFeatures: " + features + "\nPrice: " + price + "\nCapacity: " + capacity;
    }
}
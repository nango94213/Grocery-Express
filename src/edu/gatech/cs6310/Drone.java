package edu.gatech.cs6310;

import java.util.TreeMap;

public class Drone {
    public String droneId;
    public Integer capacity;
    public Integer fuel;
    public Integer remaining_cap;
    public Pilot pilot;
    public TreeMap<String, Order> order_list = new TreeMap<String, Order>();

    public Drone(String droneId, Integer capacity, Integer fuel) {
        this.droneId = droneId;
        this.capacity = capacity;
        this.remaining_cap = capacity;
        this.fuel = fuel;
        this.pilot = null;
    }


}

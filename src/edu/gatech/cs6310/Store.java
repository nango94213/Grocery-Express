package edu.gatech.cs6310;

import java.util.Map;
import java.util.TreeMap;

public class Store {
    public String name;
    public Integer revenue;
    public TreeMap<String, Item> item_list = new TreeMap<String, Item>();
    public TreeMap<String, Drone> drone_list = new TreeMap<String, Drone>();
    public TreeMap<String, Order> order_list = new TreeMap<String, Order>();


    public Store(String name, int revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    public void display() {
        System.out.println("name:" + name + "," + "revenue:" + String.valueOf(revenue));
    }

    public void addItem(String name, String weight) {
        Item i = new Item(name, Integer.parseInt(weight));
        item_list.put(name, i);
    }

    public void addDrone(String droneId, Integer capacity, Integer fuel) {
        Drone d = new Drone(droneId, capacity, fuel);
        drone_list.put(droneId, d);
    }

    public void displayItems() {
        for (Map.Entry<String, Item> entry : item_list.entrySet()) {
            Item i = entry.getValue();
            System.out.println(i.name + "," + String.valueOf(i.weight));
        }
    }

    public void displayOrder() {
        for (Map.Entry<String, Order> entry : order_list.entrySet()) {
            Order o = entry.getValue();
            System.out.println("orderID:" + o.orderId);
            o.displayDetail();
        }
    }

    public void displayDrones() {
        for (Map.Entry<String, Drone> entry : drone_list.entrySet()) {
            Drone d = entry.getValue();
            String p;
            if (d.pilot == null) {
                p = "";
            } else {
                p = ",flown_by:" + d.pilot.first_name + "_" + d.pilot.last_name;
            }
            System.out.println("droneID:" + d.droneId + ",total_cap:" + String.valueOf(d.capacity) + ",num_orders:" +
                    String.valueOf(d.order_list.size()) + ",remaining_cap:" + String.valueOf(d.remaining_cap) +
                    ",trips_left:" + String.valueOf(d.fuel) + p);
        }
    }

    public void requestItem(String orderID, String itemName, Integer quantity, Integer price, Customer c) {
        Item t = item_list.get(itemName);
        Order order = order_list.get(orderID);
        Drone d = drone_list.get(order.droneId);

        Integer weight = t.weight * quantity;
        Integer cost = quantity * price;

        if (cost > c.remaining_credit) {
            System.out.println("ERROR:customer_cant_afford_new_item");
        } else {
            if (weight > d.remaining_cap) {
                System.out.println("ERROR:drone_cant_carry_new_item");
            } else {
                order.total_cost += cost;
                order.total_weight += weight;
                d.remaining_cap -= weight;
                c.remaining_credit -= cost;
                order.adddetail(itemName, quantity, weight, cost);
                System.out.println("OK:change_completed");
            }
        }
    }

    public void complete(String orderId, Customer c) {

        Order order = order_list.get(orderId);
        Drone d = drone_list.get(order.droneId);

        if (d.pilot != null) {
            if (d.fuel >= 1) {
                c.credit -= order.total_cost;
                revenue += order.total_cost;
                d.remaining_cap += order.total_weight;
                d.fuel -= 1;
                d.pilot.experience += 1;
                order.total_cost = 0;
                order.total_weight = 0;

                order_list.remove(orderId);
                c.order_list.remove(orderId);
                d.order_list.remove(orderId);

                System.out.println("OK:change_completed");
            } else {
                System.out.println("ERROR:drone_needs_fuel");
            }
        } else {
            System.out.println("ERROR:drone_needs_pilot");
        }
    }
}

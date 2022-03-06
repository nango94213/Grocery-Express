package edu.gatech.cs6310;

import java.util.Map;
import java.util.TreeMap;

public class Order {
    public String storeId;
    public String orderId;
    public String droneId;
    public String customerId;
    public Integer weight_limit;
    public Integer credit_limit;
    public Integer total_weight;
    public Integer total_cost;
    public TreeMap<String, detail> order_detail = new TreeMap<String, detail>();

    private class detail {
        private Integer quantity;
        private Integer weight;
        private Integer cost;

        private detail(Integer quantity, Integer weight, Integer cost) {
            this.quantity = quantity;
            this.weight = weight;
            this.cost = cost;
        }
    }

    public Order(String storeId, String orderId, String droneId, String customerId, Integer credit_limit,
                 Integer weight_limit) {
        this.storeId = storeId;
        this.orderId = orderId;
        this.droneId = droneId;
        this.customerId = customerId;
        this.credit_limit = credit_limit;
        this.weight_limit = weight_limit;
        this.total_weight = 0;
        this.total_cost = 0;
    }

    public void adddetail(String item_name, Integer quantity, Integer weight, Integer cost) {
        detail d = new detail(quantity, weight, cost);
        order_detail.put(item_name, d);
    }

    public void displayDetail() {
        for (Map.Entry<String, detail> entry : order_detail.entrySet()) {
            detail d = entry.getValue();
            System.out.println("item_name:" + entry.getKey() + ",total_quantity:" + String.valueOf(d.quantity) +
                    ",total_cost:" + String.valueOf(d.cost) + ",total_weight:" + String.valueOf(d.weight));
        }
    }

}

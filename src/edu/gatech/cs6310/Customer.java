package edu.gatech.cs6310;

import java.util.TreeMap;

public class Customer {
    public String account;
    public String first_name;
    public String last_name;
    public String phone;
    public Integer rating;
    public Integer credit;
    public Integer remaining_credit;
    public TreeMap<String, Order> order_list = new TreeMap<String, Order>();

    public Customer(String account, String first_name, String last_name, String phone, Integer rating, Integer credit){
        this.account = account;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.rating = rating;
        this.credit = credit;
        this.remaining_credit = credit;
    }

    public void display(){
        System.out.println("name:"+first_name+"_"+last_name+",phone:"+phone+",rating:"+String.valueOf(rating)+",credit:"+String.valueOf(credit));
    }
}

package edu.gatech.cs6310;

public class Pilot {
    public String account;
    public String first_name;
    public String last_name;
    public String phone;
    public String tax;
    public String license;
    public Integer experience;
    public Drone drone;

    public Pilot(String account, String first_name, String last_name, String phone, String tax, String license,
                 Integer experience) {
        this.account = account;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.tax = tax;
        this.license = license;
        this.experience = experience;
        this.drone = null;
    }

    public void display() {
        System.out.println("name:" + first_name + "_" + last_name + ",phone:" + phone + ",taxID:" + tax +
                ",licenseID:" + license + ",experience:" + String.valueOf(experience));
    }

}

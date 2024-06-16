package com.example.carrentalexam.models;

public class CustomerRepairCosts {
    private String name;
    private double totalRepairCosts;

    public CustomerRepairCosts() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalRepairCosts() {
        return totalRepairCosts;
    }

    public void setTotalRepairCosts(double totalRepairCosts) {
        this.totalRepairCosts = totalRepairCosts;
    }
}

package com.example.carrentalexam.models;

import java.time.LocalDate;

public class RentalContractDetails {
    private int rentalContractId;
    private String customerName;
    private String carBrand;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private int locationId;
    private String conditionOnDelivery;
    private String conditionUponReturn;
    private boolean isRentalContractEnded;

    public RentalContractDetails() {
    }

    public int getRentalContractId() {
        return rentalContractId;
    }

    public void setRentalContractId(int rentalContractId) {
        this.rentalContractId = rentalContractId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getConditionOnDelivery() {
        return conditionOnDelivery;
    }

    public void setConditionOnDelivery(String conditionOnDelivery) {
        this.conditionOnDelivery = conditionOnDelivery;
    }

    public String getConditionUponReturn() {
        return conditionUponReturn;
    }

    public void setConditionUponReturn(String conditionUponReturn) {
        this.conditionUponReturn = conditionUponReturn;
    }

    public boolean isRentalContractEnded() {
        return isRentalContractEnded;
    }

    public void setRentalContractEnded(boolean rentalContractEnded) {
        isRentalContractEnded = rentalContractEnded;
    }
}

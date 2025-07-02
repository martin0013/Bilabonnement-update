package com.example.bilabonnement.Model;

public class Car {
    private String registrationNumber;
    private String vinNumber;
    private CarStatus status;
    private String color;
    private double purchasePrice;
    private double registrationFee;
    private int mileage;
    private String location;
    private int modelId;

    private CarModel carModel;

    public enum CarStatus {
        Available,
        Rented,
        Damaged,
        Returned,
        Sold
    }

    //Isabella - her skal enum matche 100% det der står i SQL, da vi også har brugt enum der.

    public Car() {}

    public Car(String registrationNumber, String vinNumber, CarStatus status, String color,
               double purchasePrice, double registrationFee, int mileage, String location, int modelId, String imageURL) {
        this.registrationNumber = registrationNumber;
        this.vinNumber = vinNumber;
        this.status = status;
        this.color = color;
        this.purchasePrice = purchasePrice;
        this.registrationFee = registrationFee;
        this.mileage = mileage;
        this.location = location;
        this.modelId = modelId;
    }

    // Isabella - tilføjet CarModel
    public Car(String registrationNumber, String vinNumber, CarStatus status, String color,
               double purchasePrice, double registrationFee, int mileage, String location,
               int modelId, CarModel carModel) {
        this.registrationNumber = registrationNumber;
        this.vinNumber = vinNumber;
        this.status = status;
        this.color = color;
        this.purchasePrice = purchasePrice;
        this.registrationFee = registrationFee;
        this.mileage = mileage;
        this.location = location;
        this.modelId = modelId;
        this.carModel = carModel;
    }

    // Getters og setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }


    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
}

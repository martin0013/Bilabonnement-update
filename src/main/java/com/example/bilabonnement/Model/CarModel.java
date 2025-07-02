package com.example.bilabonnement.Model;

public class CarModel {
    private int modelId;
    private String brand;
    private String modelName;
    private String equipmentLevel;
    private double co2Emission;
    private double monthlyPrice; // Tilføjet felt (Isabella)

    public CarModel() {
    }

    public CarModel(int modelId, String brand, String modelName, String equipmentLevel, double co2Emission, double monthlyPrice) {
        this.modelId = modelId;
        this.brand = brand;
        this.modelName = modelName;
        this.equipmentLevel = equipmentLevel;
        this.co2Emission = co2Emission;
        this.monthlyPrice = monthlyPrice;
    }

    // Getters og setters

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getEquipmentLevel() {
        return equipmentLevel;
    }

    public void setEquipmentLevel(String equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(double co2Emission) {
        this.co2Emission = co2Emission;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
}

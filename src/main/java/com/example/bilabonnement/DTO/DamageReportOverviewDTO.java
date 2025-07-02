package com.example.bilabonnement.DTO;

import java.time.LocalDate;

/**
 * DTO til visning af skadede biler i skadeoversigten.
 * Bruges til at overføre kun de nødvendige oplysninger fra backend til frontend.
 * Udarbejdet af Martin
 */
public class DamageReportOverviewDTO {
    private String registrationNumber;
    private String brand;
    private String modelName;
    private String description;
    private double price;
    private LocalDate reportDate;
    private boolean isOld;

    // Getters og Setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public boolean isOld() {
        return isOld;
    }

    public void setOld(boolean old) {
        isOld = old;
    }
}

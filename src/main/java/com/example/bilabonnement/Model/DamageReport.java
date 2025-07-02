package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class DamageReport {
    private int reportId;
    private LocalDate date;
    private String description;
    private double price;
    private int leaseId;
    private String inspector;
    private String registrationNumber;
    private boolean customerPresent;
    private String status;


    // Tom konstruktør
    public DamageReport() {
    }

    // Konstruktør
    public DamageReport(int reportId, LocalDate date, String description, double price, int leaseId,
                        String inspector, boolean customerPresent, String status,
                        String registrationNumber) {
        this.reportId = reportId;
        this.date = date;
        this.description = description;
        this.price = price;
        this.leaseId = leaseId;
        this.inspector = inspector;
        this.customerPresent = customerPresent;
        this.status = status;
        this.registrationNumber = registrationNumber;

    }

    // Gettere og settere
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public boolean isCustomerPresent() {
        return customerPresent;
    }

    public void setCustomerPresent(boolean customerPresent) {
        this.customerPresent = customerPresent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}


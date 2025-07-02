package com.example.bilabonnement.Model;

import java.sql.Date;

public class LeaseAgreement {

    private int leaseId;
    private Date startDate;
    private Date endDate;
    private int startMileage;
    private int endMileage;
    private double monthlyPrice;
    private double totalPrice;
    private String leaseType;
    private String status;
    private String carRegistrationNumber;
    private int userId;
    private int customerId;
    private int locationId;


    public LeaseAgreement(){}

    // Fuld constructor med alle felter
    public LeaseAgreement(int leaseId, Date startDate, Date endDate, int startMileage, int endMileage,
                          double monthlyPrice, double totalPrice, String leaseType, String status,
                          String carRegistrationNumber, int userId, int customerId, int locationId) {
        this.leaseId = leaseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startMileage = startMileage;
        this.endMileage = endMileage;
        this.monthlyPrice = monthlyPrice;
        this.totalPrice = totalPrice;
        this.leaseType = leaseType;
        this.status = status;
        this.carRegistrationNumber = carRegistrationNumber;
        this.userId = userId;
        this.customerId = customerId;
        this.locationId = locationId;
    }

    // Sumaya - Getters
    public int getLeaseId()                         { return leaseId; }
    public Date getStartDate()                      { return startDate; }
    public Date getEndDate()                        { return endDate; }
    public int getStartMileage()                    { return startMileage; }
    public int getEndMileage()                      { return endMileage; }
    public double getMonthlyPrice()                 { return monthlyPrice; }
    public double getTotalPrice()                   { return totalPrice; }
    public String getLeaseType()                    { return leaseType; }
    public String getStatus()                       { return status; }
    public String getCarRegistrationNumber()        { return carRegistrationNumber; }
    public int getUserId()                          { return userId; }
    public int getCustomerId()                      { return customerId; }
    public int getLocationId()                      { return locationId; }

    // Sumaya - Setters
    public void setLeaseId(int leaseId)                                 { this.leaseId = leaseId; }
    public void setStartDate(Date startDate)                            { this.startDate = startDate; }
    public void setEndDate(Date endDate)                                { this.endDate = endDate; }
    public void setStartMileage(int startMileage)                       { this.startMileage = startMileage; }
    public void setEndMileage(int endMileage)                           { this.endMileage = endMileage; }
    public void setMonthlyPrice(double monthlyPrice)                    { this.monthlyPrice = monthlyPrice; }
    public void setTotalPrice(double totalPrice)                        { this.totalPrice = totalPrice; }
    public void setLeaseType(String leaseType)                          { this.leaseType = leaseType; }
    public void setStatus(String status)                                { this.status = status; }
    public void setCarRegistrationNumber(String carRegistrationNumber)  { this.carRegistrationNumber = carRegistrationNumber; }
    public void setUserId(int userId)                                   { this.userId = userId; }
    public void setCustomerId(int customerId)                           { this.customerId = customerId; }
    public void setLocationId(int locationId)                           { this.locationId = locationId; }
}
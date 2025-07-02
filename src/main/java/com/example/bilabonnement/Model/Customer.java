package com.example.bilabonnement.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.List;

/* Jakarta Bean Validation bruges til at sikre at objektets tilstand er gyldig,
 inden det gemmes eller behandles videre. */

public class Customer {


    /*
     * Unikt ID for kunden.
     * @NotNull sikrer, at der altid er angivet et ID (int kan aldrig være null,
     * men hvis vi brugte Integer ville null blive fanget).
     */
    @NotNull(message = "Customer ID cannot be null")
    private int customerId;

    /*
     * Kundens fulde navn.
     * @NotNull + @Size(min = 1) sikrer, at der er indtastet et navn, og at det ikke blot er en tom string.
     */
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name cannot be empty")
    private String customerName;

    /*
     * Kundens e-mailadresse.
     * @NotNull sikrer, at feltet ikke er tomt, og @Email tjekker syntaksen,
     * der bliver også tjekket om email eksisterer.
     */
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    /*
     * Kundens telefonnummer (10 cifre).
     * @NotNull sikrer, at feltet ikke er tomt.
     * @Size(min = 10, max = 10) sikrer præcis 10 tegn (målrettet dansk mobilformat uden +45).
     */
    @NotNull(message = "Phone number cannot be null")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;

    /*
     * Kundens CPR-nummer (danske personnumre).
     * @NotNull sikrer, at CPR altid er angivet.
     */
    @NotNull(message = "CPR number cannot be null")
    private String cprNumber;

    /*
     * Adresse-objekt, f.eks. med land, gade, postnummer og by.
     * Ikke annoteret, så her kan Address selv stå for sin validering.
     */
    private Address address;  // Use Address object

    @NotNull(message = "Creation date cannot be null")
    private LocalDate createdAt;

    private boolean isActive;

    // Constructor
    public Customer() {}

    // Constructor
    public Customer(int customerId, String customerName, String email, String phoneNumber,
                    String cprNumber, Address address, LocalDate createdAt, boolean isActive) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cprNumber = cprNumber;
        this.address = address;  // Set address object
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    // Getters og Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public interface CustomerService {
        List<Customer> getAllCustomers();
    }

}

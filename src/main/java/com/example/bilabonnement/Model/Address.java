package com.example.bilabonnement.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Address {

    /* Forskel på NotNull og NotEmpty annotationerne er at @NotNull sikrer, at feltet ikke er null,
     men det kan godt være en tom streng (f.eks. "") eller en tom samling.
     @NotEmpty sikrer, at feltet ikke er null og ikke er tomt, så det kan ikke være en tom streng ("") eller en tom samling.
     */

    /*
     * Unikt ID for adressen.
     * @NotNull sikrer, at addressId altid er angivet (bemærk: med primitiv int kan det teknisk ikke være null,
     *            men hvis vi skifter til Integer, vil null blive fanget).
     */
    @NotNull(message = "Address ID cannot be null")
    private int addressId;

    /*
     * Gade-/vejnavn for adressen.
     * @NotEmpty sikrer, at strengfeltet ikke er null eller tomt.
     */
    @NotEmpty(message = "Street cannot be empty")
    private String street;

    /*
     * Byen hvor adressen ligger.
     * @NotEmpty sikrer, at byen er angivet og ikke er en tom streng.
     */
    @NotEmpty(message = "City cannot be empty")
    private String city;

    /*
     * Postnummer.
     * @NotEmpty sikrer, at postnummeret er angivet og ikke er tomt.
     */
    @NotEmpty(message = "Zip code cannot be empty")
    private String zip;

    /*
     * Land.
     * @NotEmpty sikrer, at landet er angivet og ikke er en tom streng.
     */
    @NotEmpty(message = "Country cannot be empty")
    private String country;

    public Address() {}

    public Address(int addressId, String street, String city, String zip, String country) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    // Getters and Setters
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

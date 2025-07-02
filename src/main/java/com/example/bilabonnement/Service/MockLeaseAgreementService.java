package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Service.Interface.LeaseAgreementServiceInterface;

import java.util.ArrayList;
import java.util.List;

// test dummy klasse så vi kan teste vores metoder uden at bruge databasen - Isabella
public class MockLeaseAgreementService implements LeaseAgreementServiceInterface {

    @Override
    public List<LeaseAgreement> fetchAll() {
        return new ArrayList<>();
    }

    @Override
    public void addLeaseAgreement(LeaseAgreement l) {
        System.out.println("Mock: addLeaseAgreement kaldt");
    }

    @Override
    public double calculateTotalPrice(LeaseAgreement l) {
        return 1234.56;
    }

    @Override
    public int getActiveLeaseCount() {
        return 2;
    }

    @Override
    public double getTotaltPriceOfLeasedCars() {
        return 56789.0;
    }

    @Override
    public boolean deleteLeaseAgreement(int id) {
        System.out.println("Mock: deleteLeaseAgreement kaldt med id = " + id);
        return true;
    }

    @Override
    public LeaseAgreement findById(int id) {
        System.out.println("Mock: findById kaldt med id = " + id);
        return new LeaseAgreement(); // Returnér evt. dummy-data her
    }

    @Override
    public void updateLeaseAgreement(int id, LeaseAgreement leaseAgreement) {
        System.out.println("Mock: updateLeaseAgreement kaldt med id = " + id);
    }
}

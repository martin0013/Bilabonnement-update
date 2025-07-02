package com.example.bilabonnement.Service.Interface;

import com.example.bilabonnement.Model.LeaseAgreement;
import java.util.List;


//Isabella - Vi definerer metoderne fra lease agreement service (og fake) i dette interface - og de skal have disse metoder
// Vi bruger interface i controlleren for at opnå løs kobling, så controlleren ikke er afhængig af en bestemt klasse, men kun hvad servicen kan gøre

public interface LeaseAgreementServiceInterface
{
    List<LeaseAgreement> fetchAll();

    void addLeaseAgreement(LeaseAgreement leaseAgreement);

    double calculateTotalPrice(LeaseAgreement leaseAgreement);

    int getActiveLeaseCount();

    double getTotaltPriceOfLeasedCars();

    boolean deleteLeaseAgreement(int id);

    LeaseAgreement findById(int id);

    void updateLeaseAgreement(int id, LeaseAgreement leaseAgreement);

}

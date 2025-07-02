package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service-lag for kundehåndtering.

 * Denne klasse omslutter forretningslogikken omkring Customer-objekter,
 * koordinerer transaktioner og interagerer med CustomerRepo for databaseadgang.
 */
@Service
public class CustomerService {

    // Injicerer CustomerRepo til adgang til kunde- og adresse-tabellerne
    @Autowired
    private CustomerRepo customerRepo;

    /**
     * Konstruktor til manuel injection af CustomerRepo.
     * Spring vil automatisk kalde denne, hvis der kun er én passende bean.
     *
     * @param customerRepo Repository-instans til databaseoperationer på kunder
     */
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    /**
     * Hent alle kunder inklusive tilhørende adresser.
     *
     * @return Liste af Customer-objekter med indlejrede Address
     */
    public List<Customer> getAllCustomers() {
        return customerRepo.findAllWithAddress();
    }

    /**
     * Tilføj kunde og adresse som én samlet transaktion.
     *
     * @param customer Customer-objekt, der indeholder både kunde- og adresseinfo
     *
     * Når metoden er annoteret med @Transactional, sikrer Spring,
     * at både addAddress og addCustomer enten begge lykkes
     * eller begge rulles tilbage ved fejl.
     */
    @Transactional
    public void addCustomerWithAddress(Customer customer) {
        // Indsæt adressen først for at få genereret address_id
        customerRepo.addAddress(customer.getAddress());
        // Indsæt herefter kunden med den korrekte address_id
        customerRepo.addCustomer(customer);
    }

    /**
     * Søg efter kunder baseret på navn, e-mail eller telefonnummer.
     *
     * @param keyword Fritekst-søgeterm, der bruges til case-insensitive match
     * @return Liste af Customer-objekter, der matcher søgeordet
     */
    public List<Customer> searchCustomers(String keyword) {
        return customerRepo.searchWithAddress(keyword);
    }

    /**
     * Hent den senest oprettede kunde med tilhørende adresse.
     *
     * @return Det sidste Customer-objekt målt på created_date
     */
    public Customer getLastCreatedCustomer() {
        return customerRepo.getLastCreatedCustomer();
    }

}

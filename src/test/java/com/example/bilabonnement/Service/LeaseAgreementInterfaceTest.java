package com.example.bilabonnement.Service;

import com.example.bilabonnement.Service.Interface.LeaseAgreementServiceInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Isabella
public class LeaseAgreementInterfaceTest
{

    @Test
    void shouldReturnExpectedDataFromMockService()
    {
        // Vi bruger mock Service i stedet for den rigtige Service klasse
        LeaseAgreementServiceInterface service = new MockLeaseAgreementService();

        // Vi kalder på metoden i mock
        int activeLeases = service.getActiveLeaseCount();
        double totalPrice = service.getTotaltPriceOfLeasedCars();

        // Vi forventer præcis det vi har indsat i mock klassen
        assertEquals(2, activeLeases); // Mock returnerer 2
        assertEquals(56789.0, totalPrice); // Mock returnerer 56789.0
    }
}
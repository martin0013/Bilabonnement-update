package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.LeaseAgreement;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


//Isabella - test af total pris for en måneds leje
public class LeaseAgreementServiceTest
{
    LeaseAgreementService leaseAgreementService = new LeaseAgreementService();

    @Test
    void calculateTotalPrice_ReturnMonthlyPriceFor30Days()
    {
        LeaseAgreement lease = new LeaseAgreement(); //Vi opretter et tomt objekt af klassen vi vil teste "en dummy lejeaftale"

        lease.setMonthlyPrice(3000.0);
        lease.setStartDate(Date.valueOf(LocalDate.of(2024, 6, 1))); //vi konverterer Date til LocalDate da det er LocalDate vi bruger i metoden til at
        lease.setEndDate(Date.valueOf(LocalDate.of(2024, 7, 1))); //beregne antal dage mellem to datoer

        double result = leaseAgreementService.calculateTotalPrice(lease); //vi kalder på metoden til at beregne pris

        assertEquals(3000.0, result, 0.01); //vores forventede resultet 0.01 betyder den max på afvige med det

    }

    //Isabella - test af exceptions flow, slutdato før startdato
    @Test
    void calculateTotalPrice_shouldThrowExceptionWhenEndDateIsBeforeStartDate()
    {
        LeaseAgreement lease = new LeaseAgreement();
        lease.setMonthlyPrice(3000.0);
        lease.setStartDate(Date.valueOf(LocalDate.of(2024, 7, 1)));
        lease.setEndDate(Date.valueOf(LocalDate.of(2024, 6, 1)));

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            leaseAgreementService.calculateTotalPrice(lease);
        });
        assertEquals("Slutdato skal være efter startdato", exception.getMessage());
    }
}

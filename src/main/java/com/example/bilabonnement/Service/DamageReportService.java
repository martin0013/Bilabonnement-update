package com.example.bilabonnement.Service;

import com.example.bilabonnement.DTO.DamageReportOverviewDTO;
import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.DamageReport;
import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Repository.CarRepo;
import com.example.bilabonnement.Repository.DamageReportRepo;
import com.example.bilabonnement.Repository.LeaseAgreementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DamageReportService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private DamageReportRepo damageReportRepo;

    @Autowired
    private LeaseAgreementRepo leaseAgreementRepo;

    // Returnerer en liste med alle skadesrapporter
    public List<DamageReport> findAll() {
        return damageReportRepo.findAll();
    }

    // Finder én skadesrapport baseret på rapportens ID
    public DamageReport findById(int id) {
        return damageReportRepo.findById(id);
    }

    // Finder alle skadesrapporter, der er knyttet til en bestemt bil
    public List<DamageReport> findByCarId(int carId) {
        return damageReportRepo.findByLeaseId(carId);
    }

    public void save(DamageReport report) {
        Car car = carRepo.findCarByRegistration(report.getRegistrationNumber());


        if (car.getStatus() != Car.CarStatus.Returned && car.getStatus() != Car.CarStatus.Damaged) {
            throw new IllegalStateException("Skader kan kun registreres på returnerede biler.");
        }
        if (report.getPrice() < 0) {
            throw new IllegalArgumentException("Prisen må ikke være negativ.");
        }
        if (report.getDate() == null) {
            report.setDate(LocalDate.now());
        }
        report.setStatus("Afventer");

        LeaseAgreement lease = leaseAgreementRepo.findActiveLeaseByRegistrationNumber(report.getRegistrationNumber());
        report.setLeaseId(lease.getLeaseId());


        damageReportRepo.save(report);
    }


    // Opdaterer en eksisterende skadesrapport
    public void update(DamageReport report) {
        damageReportRepo.update(report);
    }

    // Sletter en skadesrapport ud fra dens ID
    public void deleteById(int id) {
        damageReportRepo.deleteById(id);
    }

    // Martin: Returnerer liste over aktive skader og markerer gamle (over 10 dage)
    public List<DamageReportOverviewDTO> getActiveDamageReports() {
        List<DamageReportOverviewDTO> list = damageReportRepo.findActiveDamageReports();
        LocalDate today = LocalDate.now();

        for (DamageReportOverviewDTO d : list) {
            // Martin: Tjekker om skaden er ældre end 10 dage
            d.setOld(d.getReportDate().isBefore(today.minusDays(10)));
        }

        return list;
    }

}

package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.DTO.CarWithModelDTO;
import com.example.bilabonnement.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    /*----------------------------------------------------------------------------------------------------------------*/
    // bliver brugt i CarController
    /*----------------------------------------------------------------------------------------------------------------*/

    // Henter alle biler
    public List<Car> fetchAll() {
        return carRepo.fetchAll();
    }

    //2 - Bliver brugt i CarController
    // Isabella - Hent alle biler med modelinfo (DTO)
    public List<CarWithModelDTO> fetchAllCarsWithModel()
    {
        return carRepo.fetchAllCarsWithModel();
    }

    //3
    // Tilføjer ny bil - Bliver brugt i CarController
    public void addCar(Car car) {
        carRepo.addCar(car);
    }

    //4 - Bliver brugt i CarController
    // Finder bil ud fra registreringsnummer
    public Car findCarByRegistration(String registrationNumber) {
        return carRepo.findCarByRegistration(registrationNumber);
    }



    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/



    /*-----------------------------------------------------------------------------------------------------------------*/
    //bliver brugt i DashboardController
    /*----------------------------------------------------------------------------------------------------------------*/
    //9
    /*Isabella - Se status på biler i dashboard*/
    public Map<String, Integer> getCarStatusOverview() {
        return carRepo.getCarCountByStatus();
    }


    //10
    // Isabella - Henter status-fordeling pr. modelnavn til dashboard
    public Map<String, Map<String, Integer>> getStatusCountsGroupedByModel() {
        return carRepo.getStatusCountsGroupedByModel();
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------*/


    /*----------------------------------------------------------------------------------------------------------------*/
    // bliver brugt i LeaseAgreementController
    /*----------------------------------------------------------------------------------------------------------------*/
    //6
    public void updateCar(String registrationNumber, Car car) {
        carRepo.updateCar(registrationNumber, car);
    }

    //1 - 1.1 LeaseAgreement
    //Sumaya - Ændrer status på alle biler, og vises i dropDown i LeaseAgreement
    public List<Car> getAvailableCars() {
        // Henter alle biler fra databasen via carRepo
        List<Car> allCars = (List<Car>) carRepo.fetchAll();

        // Opretter en ny liste til biler, der er ledige
        List<Car> availableCars = new ArrayList<>();

        // Gennemgår alle biler og tilføjer kun dem med status "Available"
        for (Car car : allCars) {
            if (car.getStatus() == Car.CarStatus.Available) {
                availableCars.add(car);
            }
        }

        return availableCars;
    }

    //4 - 4.4 LeaseAgreement
    //Sumaya - opdatere bilerne efter deres status
    public void updateCarStatus(String registrationNumber, String newStatus) {
        // Finder bilen i databasen ud fra dens registreringsnummer
        Car car = carRepo.findCarByRegistration(registrationNumber);

        // Sætter bilens nye status ud fra en tekststreng (f.eks. "Available", "Rented")
        car.setStatus(Car.CarStatus.valueOf(newStatus));

        // Opdaterer bilens information i databasen
        carRepo.updateCar(registrationNumber, car);
    }


    // LeaseAgreement
    public double findMonthlyPriceByRegistration(String registrationNumber) {
        return carRepo.findMonthlyPriceByRegistration(registrationNumber);
    }
}



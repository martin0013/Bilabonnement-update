package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.CarModel;
import com.example.bilabonnement.Repository.CarModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {

    @Autowired
    private CarModelRepo carModelRepo;

    // Hent alle modeller
    public List<CarModel> fetchAll() {
        return carModelRepo.fetchAll();
    }
    /*
    // Find én model
    public CarModel findById(int modelId) {
        return carModelRepo.findById(modelId);
    }

    // Tilføj ny model
    public void addCarModel(CarModel model) {
        carModelRepo.addCarModel(model);
    }

    // Opdater model
    public void updateCarModel(CarModel model) {
        carModelRepo.updateCarModel(model);
    }

    // Slet model
    public boolean deleteById(int modelId) {
        return carModelRepo.deleteById(modelId);
    }*/
}

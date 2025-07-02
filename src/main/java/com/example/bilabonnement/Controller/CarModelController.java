/*package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.CarModel;
import com.example.bilabonnement.Service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    // Vis alle bilmodeller
    @GetMapping("/carmodels")
    public String getAllModels(Model model) {
        List<CarModel> carModels = carModelService.fetchAll();
        model.addAttribute("carModels", carModels);
        return "carmodel/index"; // templates/carmodel/index.html
    }

    // Vis formular til oprettelse
    @GetMapping("/carmodels/create")
    public String create(Model model) {
        model.addAttribute("carModel", new CarModel());
        return "carmodel/create"; // templates/carmodel/create.html
    }

    // Gem ny model
    @PostMapping("/carmodels/add")
    public String add(@ModelAttribute CarModel carModel) {
        carModelService.addCarModel(carModel);
        return "redirect:/carmodels";
    }

    // Vis formular til redigering
    @GetMapping("/carmodels/edit/{modelId}")
    public String edit(@PathVariable("modelId") int modelId, Model model) {
        CarModel carModel = carModelService.findById(modelId);
        model.addAttribute("carModel", carModel);
        return "carmodel/edit"; // templates/carmodel/edit.html
    }

    // Gem opdateret model
    @PostMapping("/carmodels/update")
    public String update(@ModelAttribute CarModel carModel) {
        carModelService.updateCarModel(carModel);
        return "redirect:/carmodels";
    }

    // Slet model
    @GetMapping("/carmodels/delete/{modelId}")
    public String delete(@PathVariable("modelId") int modelId) {
        carModelService.deleteById(modelId);
        return "redirect:/carmodels";
    }
}*/

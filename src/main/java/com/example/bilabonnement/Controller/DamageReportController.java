package com.example.bilabonnement.Controller;

import com.example.bilabonnement.DTO.DamageReportOverviewDTO;
import com.example.bilabonnement.Model.DamageReport;
import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.DamageReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DamageReportController {

    @Autowired
    private DamageReportService damageReportService;

    // Vis alle skadesrapporter
    @GetMapping("/damages")
    public String index(Model model) {
        List<DamageReport> reports = damageReportService.findAll();
        model.addAttribute("damageReports", reports);
        return "damage/index"; // templates/damage/index.html
    }

    // Vis formular til oprettelse af ny skade
    @GetMapping("/damages/create")
    public String create(HttpSession session, Model model) {
        // top‐wrapper: check for logged‐in user
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        // original logic
        model.addAttribute("damageReport",
                new DamageReport());
        return "damage/create"; // templates/damage/create.html
    }


    // Gem ny skadesrapport
    /*@PostMapping("/damages/createNew")
    public String createNew(@ModelAttribute DamageReport damageReport,
    @SessionAttribute("user") User currentUser) {
        damageReport.setInspection(currentUser.getUsername());
        damageReportService.save(damageReport);
        return "redirect:/damages";
    }*/

    @PostMapping("/damages/createNew")
    public String createNew(@ModelAttribute DamageReport damageReport, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");

        if (currentUser != null) {
            damageReport.setInspector(currentUser.getUsername());
        } else {
            damageReport.setInspector("admin");
        }

        damageReportService.save(damageReport);

        model.addAttribute("damageReport", new DamageReport());
        model.addAttribute("successMessage", "Skaden blev oprettet");
        model.addAttribute("user", currentUser); // for at undgå Thymeleaf-fejl

        return "damage/create";
    }

    // Vis en specifik skadesrapport
    @GetMapping("/damages/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("damageReport", damageReportService.findById(id));
        return "damage/viewOne"; // templates/damage/viewOne.html
    }

    // Slet en skadesrapport
    @GetMapping("/damages/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id) {
        damageReportService.deleteById(id);
        return "redirect:/damages";
    }

    // Vis formular til redigering
    @GetMapping("/damages/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("damageReport", damageReportService.findById(id));
        return "damage/updateOne"; // templates/damage/updateOne.html
    }

    // Gem ændringer i skadesrapport
    @PostMapping("/damages/updateReport")
    public String updateReport(@ModelAttribute DamageReport damageReport) {
        damageReportService.update(damageReport);
        return "redirect:/damages";
    }

    // Martin: Viser skadeoversigten
    @GetMapping("/damage/overview")
    public String showDamageOverview(Model model, HttpSession session) {
        // Martin: Tilføj bruger til modellen for topbaren
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        // Martin: Hent listen af aktive skader
        List<DamageReportOverviewDTO> damageList = damageReportService.getActiveDamageReports();
        model.addAttribute("damageList", damageList);

        return "damage/overview";
    }


   /* // Martin: Viser startsiden med knapper til oversigt og opret skade
    @GetMapping("/damage")
    public String damageIndex(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user); // <- Martin: Lægger brugeren ind i modellen
        return "damage/index";
    }*/

    @GetMapping("/damage")
    public String damageIndex(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("damageList", damageReportService.getActiveDamageReports());
        model.addAttribute("activeTab", "overview"); // så den står på tab 1
        return "damage/index";
    }









}

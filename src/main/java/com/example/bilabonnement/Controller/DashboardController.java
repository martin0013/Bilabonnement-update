package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.CarService;
import com.example.bilabonnement.Service.LeaseAgreementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class DashboardController
{
    @Autowired
    private LeaseAgreementService leaseAgreementService;

    @Autowired
    private CarService carService;


    @GetMapping("/business/dashboard")
    public String dashboard(HttpSession session, Model model) //Session henter bruger der er logget ind, vi lægger data i model som thymeleaf kan bruge i HTML
    {
        User user = (User) session.getAttribute("user"); //vi henter brugeren fra sessionen, den blev sat når user loggede ind

        if (user == null || user.getUserRole() != User.UserRole.FORRETNINGSUDVIKLER && user.getUserRole() != User.UserRole.ADMIN) //sikrer at det kun er forretningsudviklere og admin der kan se dashboard (da man kan ændre html i browseren)
        {
            return "redirect:/";
        }

        int activeLeases = leaseAgreementService.getActiveLeaseCount();
        double totalPrice = leaseAgreementService.getTotaltPriceOfLeasedCars();

        //Isabella - Se status på biler i dashboard
        Map<String, Integer> carStatusCounts = carService.getCarStatusOverview();

        //Isabella - metode til at vise status og modelnavn på biler i databasen
        /*List<Map<String, Object>> statusModelCounts = carService.getCarCountByStatusAndModel();*/

        //Isabella - metode til at vise status-fordeling pr. modelnavn i databasen
        Map<String, Map<String, Integer>> groupedStatusByModel = carService.getStatusCountsGroupedByModel();

        //Her gør vi tallene klar til at blive vist i Thymeleaf
        model.addAttribute("activeLeases", activeLeases);

        model.addAttribute("totalPrice", totalPrice);

        model.addAttribute("user", user);

        model.addAttribute("carStatusCounts", carStatusCounts);

        /*model.addAttribute("statusModelCounts", statusModelCounts);*/

        model.addAttribute("groupedStatusByModel", groupedStatusByModel);

        return "business/dashboard";

    }



   
}

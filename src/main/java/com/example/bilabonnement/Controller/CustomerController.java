package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Address;
import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Denne controller samler alle endpoints til kundehåndtering:
 * den viser startside, oversigt, oprettelsesform og bekræftelsesside for kunder,
 * og videresender al forretningslogik til CustomerService.
 */
@Controller
public class CustomerController {

    // Injicerer CustomerService, som håndterer forretningslogikken for kunder
    @Autowired
    private CustomerService customerService;

    /**
     * Vis startsiden for kundeområdet.
     * Tjekker om brugeren er logget ind via HttpSession, ellers redirect til login ("/").
     *
     * @param session HttpSession bruges til at hente den aktuelle User.
     * @param model   Model til at overføre data til view (her kun brugeren).
     * @return Navnet på Thymeleaf-skabelonen "customer/startpage" eller redirect til "/".
     */

    @GetMapping("/customers/startpage")
    public String startpage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "customer/startpage";
    }

    /**
     * Vis oversigt over alle kunder eller søg efter kunder, hvis keyword er angivet.
     * Hvis søgeterm forekommer og ikke er blank, kaldes searchCustomers på service,
     * ellers hentes alle kunder.
     *
     * @param keyword (valgfri) Fritekst der søges på navn, e-mail eller telefonnummer.
     * @param session HttpSession til at tjekke om brugeren er logget ind.
     * @param model   Model til at overføre data til view, inkl. user, keyword og customers.
     * @return Navnet på Thymeleaf-skabelonen "customer/overview" eller redirect til "/" ved ikke-logget bruger.
     */
    @GetMapping("/customers/overview")
    public String getAllCustomers(
            @RequestParam(value = "keyword", required = false) String keyword,
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        List<Customer> customers;
        if (keyword != null && !keyword.isBlank()) {
            // Søger kunder hvis keyword er angivet
            customers = customerService.searchCustomers(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            // Henter alle kunder ellers
            customers = customerService.getAllCustomers();
        }
        model.addAttribute("customers", customers);
        return "customer/overview";
    }


    /**
     * Forbered model til oprettelse af ny kunde.
     * Sørger for at der er et Customer-objekt med tom Address,
     * så formular i view kan binde input felter korrekt.
     *
     * @param session HttpSession til at tjekke login-status.
     * @param model   Model til at overføre data til view, her Customer-objekt.
     * @return Navnet på Thymeleaf-skabelonen "customer/createCustomer" eller redirect til "/" ved ikke-logget bruger.
     */
    @GetMapping("/customers/create")
    public String create(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        // Initialiserer nyt Customer-objekt med en tom Address, så formularfelterne kan bindes
        Customer customer = new Customer();
        customer.setAddress(new Address());
        model.addAttribute("customer", customer);

        return "customer/createCustomer";
    }
    // Tilføjer selve kunden til databasen efter oprettelse


    /**
     * Håndterer POST-request for at gemme ny kunde inkl. adresse via service-laget.
     * Udfører redirect til bekræftelsesside ved succes, eller tilbage til oprettelsesform med fejlbesked.
     *
     * @param customer           Customer-objekt bundet fra formular via @ModelAttribute.
     * @param redirectAttributes RedirectAttributes til at sende flash-attributter (fx fejlbesked).
     * @return Redirect til "/customers/confirmation" ved succes eller til "/customers/create" ved fejl.
     */
    @PostMapping("/customers/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.addCustomerWithAddress(customer);
            return "redirect:/customers/confirmation";  // Redirect til bekræftelsessiden
        } catch (IllegalArgumentException e) {
            // Fanger fejl fra service (validering) og sender fejlbesked med til oprettelsesform
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/customers/create";  // Redirect tilbage til oprettelsesform med fejl
        }
    }

    /**
     * Vis bekræftelsessiden når en kunde er oprettet.
     * Henter den sidst oprettede kunde fra service-laget og viser den sammen med en besked.
     *
     * @param model Model til at overføre data til view, her message og den oprettede Customer.
     * @return Navnet på Thymeleaf-skabelonen "customer/customerCreatedConfirmation".
     */
    @GetMapping("/customers/confirmation")
    public String confirmation(Model model) {
        Customer lastCreatedCustomer = customerService.getLastCreatedCustomer();
        model.addAttribute("message", "Kunden blev oprettet succesfuldt!");
        model.addAttribute("customer", lastCreatedCustomer);
        return "customer/customerCreatedConfirmation";   // Returnerer bekræftelsesview

    }
}
package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Repository.UserRepo;
import com.example.bilabonnement.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bilabonnement.Model.User;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController
{

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index()
    {
        return "home/index";
    }


    //1.1.3: Brug login fra service og gem bruger i sessionen (Isabella)
    //denne metode tjekker brugeren og gemmer i sessionen hvis det er en bruger med adgang og er aktiv - Isabella
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,

                        HttpSession session,
                        Model model)
    {
        User user = userService.login(username, password);
        if(user != null && user.isActive())
        {
            session.setAttribute("user", user);
            return "redirect:/intranet";
        }
        else
        {
            model.addAttribute("error", "Forkert brugernavn eller kode");
            return "home/index";
        }
    }


    //1.2.1: Opret GET /intranet-metode, der tjekker session (Isabella)
    @GetMapping("/intranet")
    public String intranet(HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");

        if(user == null)
        {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        return "home/intranet";
    }

    // 1.2.2: Tilføj GET /logout som nulstiller session (Isabella)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //--------------------------------------------------------------------------------------------------------------

}
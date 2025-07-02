package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Vis alle brugere
    /*@GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/index"; // templates/user/index.html
    }

    // Vis formular til oprettelse af ny bruger
    @GetMapping("/users/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/create"; // templates/user/create.html
    }

    // Gem ny bruger
    @PostMapping("/users/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    // Vis specifik bruger
    @GetMapping("/users/viewOne/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/viewOne"; // templates/user/viewOne.html
    }

    // Vis formular til redigering
    @GetMapping("/users/updateOne/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/updateOne"; // templates/user/updateOne.html
    }

    // Gem ændringer
    @PostMapping("/users/updateUser")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    // Slet bruger
    @GetMapping("/users/deleteOne/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }*/

    //Isabella - login
    /*@PostMapping("/users/login")
    public String validateLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user); // GEM BRUGER I SESSION
            return "redirect:/intranet";
        } else {
            model.addAttribute("error", "Ugyldigt brugernavn eller adgangskode");
            return "user/login";
        }
    }*/


}

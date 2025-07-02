package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    //1.1.2: Service-metode der kalder login-metoden i repo (Isabella)
    public User login(String username, String password) {
        return userRepo.login(username, password);
    }

}

package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class User {

        private int userId;
        private String username;
        private String password;
        private UserRole userRole;
        private boolean isActive;
        private LocalDate createdAt;


        //1.2.7: Oprette enum for relevante roller (ADMIN, SKADESMEDARBEJDER...)
        //Rollerne skal skrives med store bogstaver når vi opretter en ny user - Isabella
        public enum UserRole{
            ADMIN,
            SKADESMEDARBEJDER,
            DATAREGISTRERING,
            FORRETNINGSUDVIKLER
        }

    public User() {
        // Tom constructor
    }

    // Constructor
        public User(int userId, String username, String password, UserRole userRole, boolean isActive, LocalDate createdAt) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.userRole = userRole;
            this.isActive = isActive;
            this.createdAt = createdAt;
        }

        // Getters and setters
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setName(String name) {
            this.username = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public UserRole getUserRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public LocalDate getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
        }


    }




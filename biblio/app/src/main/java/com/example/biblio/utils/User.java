package com.example.biblio.utils;

public class User {

    String Nom;
    String Prenom;
    String Email;
    String phone;

    public User(String nom, String prenom, String email, String phone) {
        Nom = nom;
        Prenom = prenom;
        Email = email;
        this.phone = phone;
    }

    public User() {
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

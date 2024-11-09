package com.tp.webservice.tp_web_service.model;

public class Login {
    private String identifiant;
    private String password;

    public Login(String identifiant, String password) {
        this.identifiant = identifiant;
        this.password = password;
    }

    public Login() {}

    public boolean checkLogin(String identifiant, String password) {
        if (this.identifiant.equals(identifiant) && this.password.equals(password)) {
            return true;
        }
        return false;
    }
}

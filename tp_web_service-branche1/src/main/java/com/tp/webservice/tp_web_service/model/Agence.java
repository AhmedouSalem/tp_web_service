package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Agence {
    private String code;
    private String nom;
    private Adresse address;
    private List<Hotel> hotels;
    private Login login;
    public Agence(String code, String nom, Adresse address) {
        this.code = code;
        this.nom = nom;
        this.address = address;
        hotels = new ArrayList<>();
    }


    public void CreerReservation() {}

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public boolean validerAgenceLogin(Login login) {
        return this.login.getIdentifiant().equalsIgnoreCase(login.getIdentifiant()) && this.login.getPassword().equals(login.getPassword());
    }
}

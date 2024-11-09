package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

import javax.mail.Address;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Agence {
    private String code;
    private String nom;
    private Address address;
    private List<Hotel> hotels;
    private Login login;
    public Agence(String code, String nom, Address address) {
        this.code = code;
        this.nom = nom;
        this.address = address;
        hotels = new ArrayList<>();
    }

    public Agence() {}

    public void CreerReservation() {}

    public void setLogin(Login login) {
        this.login = login;
    }

    public boolean validerAgenceLogin(Login login) {
        return this.login.equals(login);
    }
}

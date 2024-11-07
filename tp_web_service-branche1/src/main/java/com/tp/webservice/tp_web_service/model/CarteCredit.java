package com.tp.webservice.tp_web_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarteCredit {
    private int numero;
    private String type;

    public CarteCredit() {}
    public CarteCredit(int numero, String type) {
        this.numero = numero;
        this.type = type;
    }

    public boolean validerCarte(int numero) {
        return numero == this.numero;
    }

    public String payerMontant(double montant) {
        return "Paiement de " + montant + " effectué avec la carte " + type;
    }
}

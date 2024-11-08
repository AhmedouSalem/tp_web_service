package com.tp.webservice.tp_web_service.model;

public class TarificationParAgence {
    private String agence;
    private Chambre chambre;
    private Double prixAdapte;

    public TarificationParAgence() {}
    public TarificationParAgence(String agence, Chambre chambre) {
        this.agence = agence;
        this.chambre = chambre;
        this.prixAdapte = chambre.getPrix();
    }

    public Double calculerPrix(Double pourcentage) {
        if (this.chambre.getNbLit() <= 1) {
            prixAdapte = this.chambre.getPrix() + (this.chambre.getPrix() * pourcentage);
            return prixAdapte;
        } else if (this.chambre.getNbLit() >= 2) {
            prixAdapte = this.chambre.getPrix() + (this.chambre.getPrix() * pourcentage);
        }
        return chambre.getPrix();
    }
}

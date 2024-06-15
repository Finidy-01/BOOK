package com.springmvc.booklibrary.models;

import java.time.LocalDate;

public class Emprunt {
    private String id;
    private String exemplaire;
    private String membre;
    private LocalDate date_pret;
    private LocalDate date_echeance;
    private LocalDate date_rendu;

    public Emprunt(String exemplaire, String membre, LocalDate date_pret, LocalDate date_echeance, LocalDate date_rendu) {
        this.setExemplaire(exemplaire);
        this.setMembre(membre);
        this.setDate_pret(date_pret);
        this.setDate_echeance(date_echeance);
        this.setDate_rendu(date_rendu);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(String exemplaire) {
        this.exemplaire = exemplaire;
    }

    public String getMembre() {
        return membre;
    }

    public void setMembre(String membre) {
        this.membre = membre;
    }

    public LocalDate getDate_pret() {
        return date_pret;
    }

    public void setDate_pret(LocalDate date_pret) {
        this.date_pret = date_pret;
    }

    public LocalDate getDate_echeance() {
        return date_echeance;
    }

    public void setDate_echeance(LocalDate date_echeance) {
        this.date_echeance = date_echeance;
    }

    public LocalDate getDate_rendu() {
        return date_rendu;
    }

    public void setDate_rendu(LocalDate date_rendu) {
        this.date_rendu = date_rendu;
    }
}

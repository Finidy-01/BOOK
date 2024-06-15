package com.springmvc.booklibrary.models;

public class RegleEmprunt {
    private String id;
    private String livre;
    private String type_membre;
    private boolean peut_emprunter;
    private boolean peut_emmener_maison;
    private Integer limite_age;
    private Integer limite_retard;

    public RegleEmprunt() {}

    public RegleEmprunt(String livre, String type_membre, boolean peut_emprunter, boolean peut_emmener_maison, Integer limite_age, Integer limite_retard) {
        this.setLivre(livre);
        this.setType_membre(type_membre);
        this.setPeut_emprunter(peut_emprunter);
        this.setPeut_emmener_maison(peut_emmener_maison);
        this.setLimite_age(limite_age);
        this.setLimite_retard(limite_retard);
    }

    public String getLivre() {
        return livre;
    }

    public void setLivre(String livre) {
        this.livre = livre;
    }

    public String getType_membre() {
        return type_membre;
    }

    public void setType_membre(String type_membre) {
        this.type_membre = type_membre;
    }

    public boolean isPeut_emprunter() {
        return peut_emprunter;
    }

    public void setPeut_emprunter(boolean peut_emprunter) {
        this.peut_emprunter = peut_emprunter;
    }

    public boolean isPeut_emmener_maison() {
        return peut_emmener_maison;
    }

    public void setPeut_emmener_maison(boolean peut_emmener_maison) {
        this.peut_emmener_maison = peut_emmener_maison;
    }

    public Integer getLimite_age() {
        return limite_age;
    }

    public void setLimite_age(Integer limite_age) {
        this.limite_age = limite_age;
    }

    public Integer getLimite_retard() {
        return limite_retard;
    }

    public void setLimite_retard(Integer limite_retard) {
        this.limite_retard = limite_retard;
    }
}

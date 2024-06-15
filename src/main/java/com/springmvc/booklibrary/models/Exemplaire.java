package com.springmvc.booklibrary.models;

public class Exemplaire {
    private String id;
    private String livre;
    private boolean disponible;

    public Exemplaire(String livre, boolean disponible) {
        this.setLivre(livre);
        this.setDisponible(disponible);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLivre() {
        return livre;
    }

    public void setLivre(String livre) {
        this.livre = livre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

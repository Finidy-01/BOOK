package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.dao.ModelDao;
import org.springframework.stereotype.Component;

@Component
public class Auteur extends ModelDao {
    private String id;
    private String nom;

    public Auteur() {
        super("auteur", "AUT", "auteur_seq");
    }

    public Auteur(String nom) {
        super("auteur", "AUT", "auteur_seq");
        this.setNom(nom);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

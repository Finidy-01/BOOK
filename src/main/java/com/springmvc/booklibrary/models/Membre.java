package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.annotations.Mapping;
import com.springmvc.booklibrary.dao.ModelDao;

import java.sql.Date;

@Mapping(table_name = "membre", id_preffix = "MBR", sequence_name = "membre_seq")
public class Membre extends ModelDao {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private Date date_naissance;
    private String type_membre;

    public Membre() {}

    public Membre(String non, String prenom, String email, Date date_naissance) {
        this.setNom(non);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setDate_naissance(date_naissance);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getType_membre() {
        return type_membre;
    }

    public void setType_membre(String type_membre) {
        this.type_membre = type_membre;
    }
}

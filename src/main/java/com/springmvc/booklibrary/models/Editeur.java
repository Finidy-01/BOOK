package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.dao.ModelDao;

public class Editeur extends ModelDao {
    private String id;
    private String designation;

    public Editeur() { super("editeur", "EDI", "editeur_seq"); }

    public Editeur(String designation) {
        super("editeur", "EDI", "editeur_seq");
        this.setDesignation(designation);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}

package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.dao.ModelDao;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class Livre extends ModelDao {
    private String id;
    private String titre;
    private String auteur;
    private String isbn;
    private String numero_cote;
    private String editeur;
    private Date date_edition;
    private Integer tome;
    private String collection;
    private String langue;
    private Integer nombre_pages;

    public Livre() { super("livre", "LIV", "livre_seq"); }

    public Livre(String titre, String auteur, String isbn, String numero_cote, String editeur, Date date_edition, Integer tome, String collection, String langue, Integer nombre_pages) {
        super("livre", "LIV", "livre_seq");
        this.setTitre(titre);
        this.setAuteur(auteur);
        this.setIsbn(isbn);
        this.setNumero_cote(numero_cote);
        this.setEditeur(editeur);
        this.setDate_edition(date_edition);
        this.setTome(tome);
        this.setCollection(collection);
        this.setLangue(langue);
        this.setNombre_pages(nombre_pages);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNumero_cote() {
        return numero_cote;
    }

    public void setNumero_cote(String numero_cote) {
        this.numero_cote = numero_cote;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Date getDate_edition() {
        return date_edition;
    }

    public void setDate_edition(Date date_edition) {
        this.date_edition = date_edition;
    }

    public Integer getTome() {
        return tome;
    }

    public void setTome(Integer tome) {
        this.tome = tome;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Integer getNombre_pages() {
        return nombre_pages;
    }

    public void setNombre_pages(Integer nombre_pages) {
        this.nombre_pages = nombre_pages;
    }
}

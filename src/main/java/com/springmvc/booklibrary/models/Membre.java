package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.annotations.Mapping;
import com.springmvc.booklibrary.dao.JdbcService;
import com.springmvc.booklibrary.dao.ModelDao;
import com.springmvc.booklibrary.dao.ObjectRowMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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

    public int getAge() {
        LocalDate birthLocalDate = this.getDate_naissance().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthLocalDate, currentDate);
        return period.getYears();
    }

    public Membre[] search(Connection con) throws SQLException {
        try {
            if (con == null) {
                return new Membre[0];
            }

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM membre").append(" WHERE ");
            if (this.getNom() != null) {
                sql.append(" nom ilike '%").append(this.getNom()).append("%' ");
                sql.append(" or prenom ilike '%").append(this.getNom()).append("%' ");
                sql.append(" and ");
            }

            if (this.getEmail() != null) {
                sql.append(" email ilike '%").append(this.getEmail()).append("%' ");
                sql.append(" and ");
            }

            sql.setLength(sql.length() - 4);
            System.out.println(sql.toString());

            List list = JdbcService.query(con, sql.toString(), new ObjectRowMapper(Membre.class));
            Membre[] result = new Membre[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = (Membre) list.get(i);
            }
            return result;

        } catch (Exception e) {
            throw new SQLException("erreur eo amle recherche livre", e);
        }

    }

    public String authenticate(Connection con) throws SQLException {
        Membre membre = (Membre) this.get(con);
        if (membre != null) {
            return membre.getId();
        }
        return null;
    }

    public int peutEmprunter(Connection con, Livre livre, boolean veutEmmenerMaison) throws SQLException {

        RegleEmprunt regle = new RegleEmprunt();
        regle.setLivre(livre.getId());
        regle.setType_membre(this.getType_membre());
        regle = (RegleEmprunt) regle.get(con);

        if (regle == null) {
            return 3;
        }

        int age = this.getAge();
        int limiteAge = regle.getLimite_age();
        boolean peutEmprunter = regle.isPeut_emprunter();
        boolean peutEmmenerMaison = regle.isPeut_emmener_maison();

        if (!peutEmprunter) {
            return -1;
        }

        if (age < limiteAge) {
            return 0;
        }

        return peutEmmenerMaison ? 2 : 1;
    }

    public boolean estPenalise(Connection con) throws SQLException {
        PenaliteMembre penalite = new PenaliteMembre();
        penalite.setMembre(this.getId());
        penalite = (PenaliteMembre) penalite.get(con);
        if (penalite != null) {
            return penalite.getEstPenalise();
        }
        return false;
    }

}

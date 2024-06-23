package com.springmvc.booklibrary.controller;

import com.springmvc.booklibrary.dao.JdbcService;
import com.springmvc.booklibrary.models.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/book-library/emprunt")
public class EmpruntController {

    @GetMapping
    public String empruntView(@RequestParam(name = "message", required = false) String message, Model model, HttpSession session) {
        String idAdmin = (String) session.getAttribute("id");
        if (idAdmin == null) {
            return "redirect:/";
        }

        Connection con = JdbcService.getConnection();

        Livre livre = new Livre();
        List<Object> livres = livre.findAll(con);
        model.addAttribute("livres", livres);

        Membre membre = new Membre();
        List<Object> membres = membre.findAll(con);
        model.addAttribute("membres", membres);

        Emprunt emprunt = new Emprunt();
        List<Object> emprunts = emprunt.findAll(con);
        model.addAttribute("emprunts", emprunts);

        if (message != null) {
            model.addAttribute("message", message);
        }

        return "pages/emprunt";
    }

    @PostMapping("/save")
    public String empruntSave(Emprunt emprunt, @RequestParam(name = "livre", required = false) String idLivre, @RequestParam(name = "emmenerMaison", required = false) String emmenerMaison) throws SQLException {
        Connection con = JdbcService.getConnection();

        String message = "";

        // mi enregistre date rendu de livre
        if (emprunt.getId() != null) {
            message = "emprunt modifie";
            Date date_rendu = emprunt.getDate_rendu();
            emprunt.setDate_rendu(null);
            emprunt = (Emprunt) emprunt.get(con);
            emprunt.setDate_rendu(date_rendu);

            if (emprunt.isPenalized()) {
                Membre membre = new Membre();
                membre.setId(emprunt.getMembre());
                membre = (Membre) membre.get(con);

                Penalite penalite = new Penalite();
                TypeMembre typeMembre = new TypeMembre();
                typeMembre.setId(membre.getType_membre());
                typeMembre = (TypeMembre) typeMembre.get(con);

                int jour_retard = emprunt.getJourRetard();

                penalite.setMembre(emprunt.getMembre());
                penalite.setDate_debut(emprunt.getDate_rendu());
                penalite.setDate_fin(typeMembre.getCoeff_retard()*jour_retard);
                penalite.save(con);
                System.out.println(String.valueOf(typeMembre.getCoeff_retard()) + "+" + String.valueOf(jour_retard));

                message += " avec penalite";
            }
            emprunt.save(con);
            con.close();
            return "redirect:/book-library/emprunt?message=" + message;
        }

        Membre membre = new Membre();
        membre.setId(emprunt.getMembre());
        membre = (Membre) membre.get(con);

        if (membre.estPenalise(con)) {
            con.close();
            message = "membre penalise";
            return "redirect:/book-library/emprunt?message=" + message;
        }

        Livre livre = new Livre();
        livre.setId(idLivre);

        if (!livre.isDispo(con)) {
            con.close();
            message = "livre non disponible";
            return "redirect:/book-library/emprunt?message=" + message;
        }

        int result = membre.peutEmprunter(con, livre, (emmenerMaison != null));
        if (result == 0) {
            con.close();
            message = "peut pas emprunter, pas assez vieux";
            return "redirect:/book-library/emprunt?message=" + message;
        }
        if (result == -1) {
            con.close();
            message = "peut pas emprunter";
            return "redirect:/book-library/emprunt?message=" + message;
        }
        if (result > 0) {
            Exemplaire exemplaire = livre.getExemplaire(con);
            emprunt.setExemplaire(exemplaire.getId());
            RegleEmprunt regleEmprunt = new RegleEmprunt();
            regleEmprunt.setLivre(livre.getId());
            regleEmprunt.setType_membre(membre.getType_membre());
            regleEmprunt = (RegleEmprunt) regleEmprunt.get(con);

            if (emprunt.save(con, regleEmprunt) > 0) {
                exemplaire.setDisponible(false);
                exemplaire.save(con);
            }

            con.close();

            if (result == 1) {
                System.out.println("peut emprunter");
                message = "emprunt accepté";
                return "redirect:/book-library/emprunt?message=" + message;
            }
            if (result == 2) {
                System.out.println("peut emmener maison");
                message = "emprunt accepté, peut emmener à la maison";
                return "redirect:/book-library/emprunt?message=" + message;
            }
            if (result == 3) {
                System.out.println("pas de regle");
                message = "emprunt accepté, pas de règle";
                return "redirect:/book-library/emprunt?message=" + message;
            }
        }

        return "redirect:/book-library/emprunt";
    }

}

// TODO : update exemplaire to not disponible (the function is already there)

package com.springmvc.booklibrary.controller;

import com.springmvc.booklibrary.dao.JdbcService;
import com.springmvc.booklibrary.models.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping("/book-library")
public class BookLibraryController {

    @GetMapping
    public String accueilView(HttpSession session, Model model) {
        String id = (String) session.getAttribute("id");
        if (id == null) {
            return "redirect:/";
        }
        model.addAttribute("id", id);
        return "pages/accueil";
    }

    // LIVRE //////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/livre")
    public String livreView(@ModelAttribute LivreSearch search_livre, Model model) throws SQLException, IllegalAccessException {
        Connection con = JdbcService.getConnection();

        if (search_livre.isEmpty()) {
            System.out.println("not searching");
            Livre livre = new Livre();
            List<Object> livres = livre.findAll(con);
            model.addAttribute("livres", livres);
        } else {
            System.out.println("searching");
            Livre[] livres = search_livre.search(con);
            model.addAttribute("livres", livres);
        }

        Auteur auteur = new Auteur();
        List<Object> auteurs = auteur.findAll(con);
        model.addAttribute("auteurs", auteurs);

        Editeur editeur = new Editeur();
        List<Object> editeurs = editeur.findAll(con);
        model.addAttribute("editeurs", editeurs);

        Collection collection = new Collection();
        List<Object> collections = collection.findAll(con);
        model.addAttribute("collections", collections);

        Langue langue = new Langue();
        List<Object> langues = langue.findAll(con);
        model.addAttribute("langues", langues);

        con.close();

        return "pages/livre";
    }

    @PostMapping("/livre/save")
    public String livreCreate(Livre input_livre) throws SQLException {
        Connection con = JdbcService.getConnection();

        input_livre.save(con);

        con.close();

        return "redirect:/book-library/livre";
    }

    @GetMapping("/livre/{id}/delete")
    public String livreDeleteView(@PathVariable("id") String id, Model model) throws SQLException {
        Connection con = JdbcService.getConnection();

        Livre livre = new Livre();
        livre.setId(id);
        livre = (Livre) livre.get(con);

        model.addAttribute("livre", livre);

        con.close();

        return "pages/livre-delete";
    }
    @PostMapping("/livre/{id}/delete")
    public String livreDeleteView(@PathVariable("id") String id) throws SQLException {
        Connection con = JdbcService.getConnection();

        Livre livre = new Livre();
        livre.setId(id);
        livre.delete(con);

        con.close();

        return "redirect:/book-library/livre";
    }

    @GetMapping("/livre/{id}/update")
    public String livreUpdateView(@PathVariable("id") String id, Model model) throws SQLException {
        Connection con = JdbcService.getConnection();

        Livre livre = new Livre();
        livre.setId(id);
        livre = (Livre) livre.get(con);
        model.addAttribute("livre", livre);

        Auteur auteur = new Auteur();
        List<Object> auteurs = auteur.findAll(con);
        model.addAttribute("auteurs", auteurs);

        Editeur editeur = new Editeur();
        List<Object> editeurs = editeur.findAll(con);
        model.addAttribute("editeurs", editeurs);

        Collection collection = new Collection();
        List<Object> collections = collection.findAll(con);
        model.addAttribute("collections", collections);

        Langue langue = new Langue();
        List<Object> langues = langue.findAll(con);
        model.addAttribute("langues", langues);

        con.close();

        return "pages/livre-update";
    }

    // MEMBRE //////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/membre")
    public String membreView(Model model) throws SQLException {
        Connection con = JdbcService.getConnection();

        Membre membre = new Membre();
        List<Object> membres = membre.findAll(con);
        model.addAttribute("membres", membres);

        TypeMembre typeMembre = new TypeMembre();
        List<Object> typeMembres = typeMembre.findAll(con);
        model.addAttribute("typeMembres", typeMembres);

        con.close();

        return "pages/membre";
    }

    @PostMapping("/membre/save")
    public String membreCreate(Membre input_membre) throws SQLException {
        Connection con = JdbcService.getConnection();

        input_membre.save(con);

        con.close();

        return "redirect:/book-library/membre";
    }

    @GetMapping("/membre/{id}/update")
    public String membreUpdateView(@PathVariable("id") String id, Model model) throws SQLException {
        Connection con = JdbcService.getConnection();

        Membre membre = new Membre();
        membre.setId(id);
        membre = (Membre) membre.get(con);
        model.addAttribute("membre", membre);

        TypeMembre typeMembre = new TypeMembre();
        List<Object> typeMembres = typeMembre.findAll(con);
        model.addAttribute("typeMembres", typeMembres);

        con.close();

        return "pages/membre-update";
    }

    @GetMapping("/membre/{id}/delete")
    public String membreDeleteView(@PathVariable("id") String id, Model model) throws SQLException {
        Connection con = JdbcService.getConnection();

        Membre membre = new Membre();
        membre.setId(id);
        membre = (Membre) membre.get(con);

        model.addAttribute("membre", membre);

        con.close();

        return "pages/membre-delete";
    }
    @PostMapping("/membre/{id}/delete")
    public String membreDeleteView(@PathVariable("id") String id) throws SQLException {
        Connection con = JdbcService.getConnection();

        Membre membre = new Membre();
        membre.setId(id);
        membre.delete(con);

        con.close();

        return "redirect:/book-library/membre";
    }

}

// EMPRUNT //////////////////////////////////////////////////////////////////////////////////////////////

// TODO: ajouter emprunt -> exemplaire devient non disponible -> ajouter emprunt dans table emprunt

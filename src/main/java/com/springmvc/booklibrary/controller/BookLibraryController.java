package com.springmvc.booklibrary.controller;

import com.springmvc.booklibrary.models.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // LIVRE
    @GetMapping("/livre")
    public String livreView(Model model) {
        Livre livre = new Livre();
        List<Object> livres = livre.findAll();
        model.addAttribute("livres", livres);

        Auteur auteur = new Auteur();
        List<Object> auteurs = auteur.findAll();
        model.addAttribute("auteurs", auteurs);

        Editeur editeur = new Editeur();
        List<Object> editeurs = editeur.findAll();
        model.addAttribute("editeurs", editeurs);

        Collection collection = new Collection();
        List<Object> collections = collection.findAll();
        model.addAttribute("collections", collections);

        Langue langue = new Langue();
        List<Object> langues = langue.findAll();
        model.addAttribute("langues", langues);

        return "pages/livre";
    }

    @PostMapping("/livre/save")
    public String livreCreate(Livre input_livre) throws SQLException {
        input_livre.save();
        return "redirect:/book-library/livre";
    }

    @GetMapping("/livre/{id}/delete")
    public String livreDeleteView(@PathVariable("id") String id, Model model) throws SQLException {
        Livre livre = new Livre();
        livre.setId(id);
        livre = (Livre) livre.get();

        model.addAttribute("livre", livre);
        return "pages/livre-delete";
    }
    @PostMapping("/livre/{id}/delete")
    public String livreDeleteView(@PathVariable("id") String id) throws SQLException {
        Livre livre = new Livre();
        livre.setId(id);
        livre.delete();

        return "redirect:/book-library/livre";
    }

    @GetMapping("/livre/{id}/update")
    public String livreUpdateView(@PathVariable("id") String id, Model model) throws SQLException {
        Livre livre = new Livre();
        livre.setId(id);
        livre = (Livre) livre.get();
        model.addAttribute("livre", livre);

        Auteur auteur = new Auteur();
        List<Object> auteurs = auteur.findAll();
        model.addAttribute("auteurs", auteurs);

        Editeur editeur = new Editeur();
        List<Object> editeurs = editeur.findAll();
        model.addAttribute("editeurs", editeurs);

        Collection collection = new Collection();
        List<Object> collections = collection.findAll();
        model.addAttribute("collections", collections);

        Langue langue = new Langue();
        List<Object> langues = langue.findAll();
        model.addAttribute("langues", langues);

        return "pages/livre-update";
    }

    // MEMBRE
    @GetMapping("/membre")
    public String membreView(Model model) {
        Livre livre = new Livre();
        List<Object> livres = livre.findAll();
        model.addAttribute("livres", livres);

        Auteur auteur = new Auteur();
        List<Object> auteurs = auteur.findAll();
        model.addAttribute("auteurs", auteurs);

        Editeur editeur = new Editeur();
        List<Object> editeurs = editeur.findAll();
        model.addAttribute("editeurs", editeurs);

        Collection collection = new Collection();
        List<Object> collections = collection.findAll();
        model.addAttribute("collections", collections);

        Langue langue = new Langue();
        List<Object> langues = langue.findAll();
        model.addAttribute("langues", langues);

        return "pages/livre";
    }


}

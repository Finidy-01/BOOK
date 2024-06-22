package com.springmvc.booklibrary.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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



}

// TODO: ajouter emprunt -> exemplaire devient non disponible -> ajouter emprunt dans table emprunt

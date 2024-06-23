package com.springmvc.booklibrary.controller;

import com.springmvc.booklibrary.models.LivrePlusEmprunte;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/book-library")
public class BookLibraryController {

    @GetMapping
    public String accueilView(HttpSession session, Model model) {
        String idAdmin = (String) session.getAttribute("id");
        if (idAdmin == null) {
            return "redirect:/";
        }

        LivrePlusEmprunte livre = new LivrePlusEmprunte();
        model.addAttribute("livre", livre);

        model.addAttribute("id", idAdmin);
        return "pages/accueil";
    }



}

// TODO: ajouter emprunt -> exemplaire devient non disponible -> ajouter emprunt dans table emprunt

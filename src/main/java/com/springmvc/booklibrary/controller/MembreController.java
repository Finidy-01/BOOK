package com.springmvc.booklibrary.controller;

import com.springmvc.booklibrary.dao.JdbcService;
import com.springmvc.booklibrary.models.Membre;
import com.springmvc.booklibrary.models.TypeMembre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/book-library/membre")
public class MembreController {

    @GetMapping
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

    @PostMapping("/save")
    public String membreCreate(Membre input_membre) throws SQLException {
        Connection con = JdbcService.getConnection();

        input_membre.save(con);

        con.close();

        return "redirect:/book-library/membre";
    }

    @GetMapping("/{id}/update")
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

    @GetMapping("/{id}/delete")
    public String membreDeleteView(@PathVariable("id") String id, Model model) throws SQLException {
        Connection con = JdbcService.getConnection();

        Membre membre = new Membre();
        membre.setId(id);
        membre = (Membre) membre.get(con);

        model.addAttribute("membre", membre);

        con.close();

        return "pages/membre-delete";
    }
    @PostMapping("/{id}/delete")
    public String membreDeleteView(@PathVariable("id") String id) throws SQLException {
        Connection con = JdbcService.getConnection();

        Membre membre = new Membre();
        membre.setId(id);
        membre.delete(con);

        con.close();

        return "redirect:/book-library/membre";
    }

    @GetMapping("/search")
    @ResponseBody
    public Membre[] membreSearch(@RequestParam("nomprenom") String nomprenom, @RequestParam("email") String email) throws SQLException {
        Connection con = JdbcService.getConnection();

        Membre membreSearch = new Membre();
        membreSearch.setNom(nomprenom);
        membreSearch.setEmail(email);

        Membre[] membres = membreSearch.search(con);
        con.close();
        return membres;
    }

}

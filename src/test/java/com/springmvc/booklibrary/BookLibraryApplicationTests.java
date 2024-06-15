package com.springmvc.booklibrary;

import com.springmvc.booklibrary.models.Auteur;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookLibraryApplicationTests {

    @Test
    void contextLoads() {
        Auteur auteur = new Auteur();
        auteur.setTable_name("auteur");
        Auteur tempAuteur = new Auteur();

        List<Object> allAuteur = auteur.findAll();
        for (int i = 0; i < allAuteur.size(); i++) {
            tempAuteur = (Auteur) allAuteur.get(i);
            System.out.println(tempAuteur.getNom());
        }
    }

}

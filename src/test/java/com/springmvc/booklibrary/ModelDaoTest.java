package com.springmvc.booklibrary;

import com.springmvc.booklibrary.dao.ModelDao;
import com.springmvc.booklibrary.models.Auteur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelDaoTest {

    @Autowired
    private ModelDao modelDao;

    private Auteur auteurTest;

    @Test
    public void contextLoads() {
        assertNotNull(modelDao);
        assertNotNull(modelDao.getJdbcTemplate());
        assertNotNull(auteurTest.getJdbcTemplate());
        System.out.println("------------------------------");
        System.out.println(auteurTest.getJdbcTemplate());
    }
}

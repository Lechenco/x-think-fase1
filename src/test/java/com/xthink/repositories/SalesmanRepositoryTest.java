package com.xthink.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import com.xthink.configuration.RepositoryConfiguration;
import com.xthink.domain.Salesman;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class SalesmanRepositoryTest {
    private SalesmanRepository repository;

    @Autowired
    public void setRepository(SalesmanRepository repository) {
        this.repository = repository;
    }
    
    @Before
    public void loadDB() {
        repository.save(new Salesman("Maria", "Souza"));
        repository.save(new Salesman("Carlos", "Ribeiro"));
        repository.save(new Salesman("Rogerio", "Silva"));
    }

    @After
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void insertSalesman() {
        Salesman salesman = new Salesman("Marcos", "José");

        try {
            assertEquals(0, salesman.getId());
            repository.save(salesman);
            assertNotEquals(0, salesman.getId());
        } catch (Exception e) {
            fail("Error in saves Salesman");
        }
        
    }

    @Test
    public void selectSalesman() {
        try {
             Salesman salesman = repository.findAll().iterator().next();
             assertNotNull(salesman);
        } catch (Exception e) {
            fail("Salesman 2 not finded." + e.getMessage());
        }
    }

    @Test 
    public void notFoundSalesman() {
        try {
            Salesman salesman = repository.findById((long) 1000).get();
            System.out.println(salesman.getFirstName() + salesman.getId());
             fail("Method find by Id does not throws Exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteSalesman() {
        Salesman salesman = repository.findAll().iterator().next();

        repository.delete(salesman);

        try {
            salesman = repository.findById(salesman.getId()).get();
            fail("Method find by Id does not trhows Exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}

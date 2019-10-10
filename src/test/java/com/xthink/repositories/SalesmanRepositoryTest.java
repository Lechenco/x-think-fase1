package com.xthink.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import com.xthink.configuration.RepositoryConfiguration;
import com.xthink.domain.Salesman;

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
    public void loadSalesman() {
        repository.save(new Salesman("Maria", "Souza"));
        repository.save(new Salesman("Carlos", "Ribeiro"));
    }

    @Test
    public void insertSalesman() {
        Salesman salesman = new Salesman("Carlos", "José");

        try {
            assertEquals(0, salesman.getId());
            repository.save(salesman);
            assertNotEquals(0, salesman.getId());
        } catch (Exception e) {
            fail("Error in save nes Salesman");
        }
    }

    @Test
    public void selectSalesman() {
        try {
            Salesman salesman = repository.findById((long) 2).get();
            assertNotNull(salesman);
        } catch (Exception e) {
            fail("Salesman not finded.");
        }
    }

    @Test 
    public void notFoundSalesman() {
        try {
            Salesman salesman = repository.findById((long) 10).get();
            fail("Method find by Id does not trhows Exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteSalesman() {
        Salesman salesman = repository.findById((long) 1).get();

        repository.delete(salesman);

        try {
            salesman = repository.findById((long) 1).get();
            fail("Method find by Id does not trhows Exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
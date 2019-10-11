package com.xthink.repositories;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import com.xthink.configuration.RepositoryConfiguration;
import com.xthink.domain.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class SaleRepositoryTest {
    private SaleRepository saleRepository;
    private SalesmanRepository salemanRepository;

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Autowired
    public void setSalemanRepository(SalesmanRepository salemanRepository) {
        this.salemanRepository = salemanRepository;
    }

    @Before
    public void loadDB() {
        salemanRepository.save(new Salesman("Maria", "Souza"));
        salemanRepository.save(new Salesman("Carlos", "Ribeiro"));
        // salemanRepository.save(new Salesman("Antonio", "João"));

        Iterator<Salesman> iterator = salemanRepository.findAll().iterator();
        Salesman maria = iterator.next();
        Salesman carlos = iterator.next();

        System.out.println(maria.getFirstName() + maria.getLastName() + maria.getId());

        saleRepository.save(new Sale(LocalDate.of(2019, 06, 21), 1000, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 06, 30), 500, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 07, 02), 2000, carlos));
    }

    @After
    public void cleanDB() {
        saleRepository.deleteAll();
        salemanRepository.deleteAll();
    }


    @Test
    public void selectSalesBetweenDatesTest() {
        List<Object[]> obj = saleRepository.selectSalesBetweenDate(
                LocalDate.of(2019, 06, 02), LocalDate.of(2019, 06, 30));

        assertEquals("Size of List unespected", 1, obj.size());
        assertEquals("Number of sales to first row with wrong answer",
                 (long) 2, obj.get(0)[1]);
    }

    @Test
    public void contextLoads() {}
}

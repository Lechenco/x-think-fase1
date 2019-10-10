package com.xthink.repositories;

import java.time.LocalDate;
import java.util.Iterator;

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

        Sale sale = new Sale();
        sale.setPrice((float) 1000.0);
        sale.setSaleDate(LocalDate.of(2019, 06, 21));
        sale.setSalesman(maria);
        saleRepository.save(sale);

        sale = new Sale();
        sale.setPrice((float) 500.0);
        sale.setSaleDate(LocalDate.of(2019, 06, 30));
        sale.setSalesman(maria);
        saleRepository.save(sale);

        sale = new Sale();
        sale.setPrice((float) 2000.0);
        sale.setSaleDate(LocalDate.of(2019, 07, 02));
        sale.setSalesman(carlos);
        saleRepository.save(sale);
    }

    @After
    public void cleanDB() {
        saleRepository.deleteAll();
        salemanRepository.deleteAll();
    }

    @Test
    public void contextLoads() {}
}

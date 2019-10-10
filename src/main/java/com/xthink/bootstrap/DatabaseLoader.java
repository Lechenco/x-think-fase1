package com.xthink.bootstrap;

import java.time.LocalDate;
import java.util.Iterator;

import com.xthink.domain.Sale;
import com.xthink.domain.Salesman;
import com.xthink.repositories.SaleRepository;
import com.xthink.repositories.SalesmanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private SalesmanRepository salesmanRepository;
    private SaleRepository saleRepository;

    @Autowired 
    public void setSalesmanRepository(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadDatabase();
    }

    public void loadDatabase() {
        
        salesmanRepository.save(new Salesman("Maria", "Souza"));
        salesmanRepository.save(new Salesman("Carlos", "Ribeiro"));
        // salemanRepository.save(new Salesman("Antonio", "João"));

        Iterator<Salesman> iterator = salesmanRepository.findAll().iterator();
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

        saleRepository.save(new Sale(LocalDate.of(2019, 10, 15), 30, maria));
    }

}

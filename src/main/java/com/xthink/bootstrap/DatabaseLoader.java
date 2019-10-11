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
        salesmanRepository.save(new Salesman("Antonio", "João"));

        Iterator<Salesman> iterator = salesmanRepository.findAll().iterator();
        Salesman maria = iterator.next();
        Salesman carlos = iterator.next();
        Salesman antonio = iterator.next();

        // Maria Sales
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 15), 30, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 12), 410, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 01), 10, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 07), 1250, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 07), 40, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 9), 30, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 13), 300, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 13), 120, maria));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 13), 330, maria));

        saleRepository.save(new Sale(LocalDate.of(2019, 10, 15), 430, carlos));
        saleRepository.save(new Sale(LocalDate.of(2019, 9, 30), 320, carlos));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 04), 310, carlos));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 17), 20, carlos));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 02), 5, carlos));
        saleRepository.save(new Sale(LocalDate.of(2019, 9, 30), 10, carlos));
        
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 21), 100, antonio));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 04), 130, antonio));
        saleRepository.save(new Sale(LocalDate.of(2019, 10, 05), 570, antonio));
    }

}

package com.xthink.vendas;

import com.xthink.domain.Salesman;
import com.xthink.repositories.SalesmanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SalesmanLoader implements ApplicationListener<ContextRefreshedEvent> {

    private SalesmanRepository repository;
    private boolean event = false;

    public boolean isEvent() {
        return event;
    }

    @Autowired 
    public void setRepository(SalesmanRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Salesman salesman = new Salesman("Joao", "Marcos");
        repository.save(salesman);
        this.event = true;
    }


}

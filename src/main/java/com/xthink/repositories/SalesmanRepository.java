package com.xthink.repositories;

import com.xthink.domain.Salesman;
import org.springframework.data.repository.CrudRepository;

public interface SalesmanRepository extends CrudRepository<Salesman, Long> {
    
}

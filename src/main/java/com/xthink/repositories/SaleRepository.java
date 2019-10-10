package com.xthink.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import com.xthink.domain.Sale;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    @Query (value = "SELECT s.salesman ,SUM(s.price), COUNT(s.salesman.id) FROM Sale s " +
    "WHERE s.saleDate BETWEEN :start AND :end " +
    "GROUP BY s.salesman.id")
    List<Object[]> selectSalesBetweenDate(@Param("start") LocalDate start,
                                        @Param("end") LocalDate end);
}

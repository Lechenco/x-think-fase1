package com.xthink.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.xthink.domain.Sale;
import com.xthink.domain.Salesman;
import com.xthink.repositories.SaleRepository;
import com.xthink.repositories.SalesmanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {

    private SaleRepository saleRepository;
    private SalesmanRepository salesmanRepository;

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Autowired
    public void setSalesmanRepository(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @RequestMapping("/")
    public String index() {
        return "Sales System: X-Think Store";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public long insertSale(long salesmanId, float price) {
        try {
            Salesman salesman = salesmanRepository.findById(salesmanId).get();
            saleRepository.save(new Sale(LocalDate.now(), price, salesman));
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    @RequestMapping(value = "/list")
    public String listBetweenDates(String start, String end) {
        LocalDate startDate, endDate;
        startDate = LocalDate.parse(start);
        endDate = LocalDate.parse(end);
        long numOfDays = startDate.until(endDate, ChronoUnit.DAYS);

        // Object array: [0] - Salesman; [1] - number of sales
        List<Object[]> obj = saleRepository.selectSalesBetweenDate(startDate, endDate);
        
        return responseFromListObjects(obj, numOfDays);
    }

    private String responseFromListObjects(List<Object[]> obj, long numOfDays) {
        String response = "";
        for (Object[] o : obj) {
            Salesman s = (Salesman) o[0];
            response += "Nome: " + s.toString() + " ";
            response += "Total Vendas: " + (long) o[1] + " "; 
            response += "Med. Vendas por Dia: " + ((long) o[1]) / (double) numOfDays + "\n";
        }
        return response;
    }
}

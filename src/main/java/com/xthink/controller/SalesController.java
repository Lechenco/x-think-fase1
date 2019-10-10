package com.xthink.controller;

import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.util.List;

import com.xthink.domain.Sale;
import com.xthink.domain.Salesman;
import com.xthink.repositories.SaleRepository;
import com.xthink.repositories.SalesmanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        String response = "";

        LocalDate startDate, endDate;
        startDate = LocalDate.parse(start);
        endDate = LocalDate.parse(end);
        List<Object[]> obj = saleRepository.selectSalesBetweenDate(startDate, endDate);
        System.out.println("+++++++++++++++++++OOOOOOOOOOOO+++++++++++++");
        for (Object[] o : obj) {
            Salesman s = (Salesman) o[0];
            System.out.println(s.getFirstName() + " " + (double) o[1] + " " +
                        (long) o[2]);
        }

        return "";
    }
}

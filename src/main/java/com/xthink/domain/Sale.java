package com.xthink.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Sale {

    @Id
    @GeneratedValue
    private long id;
    
    private LocalDate saleDate;
    private float price;

    @ManyToOne
    private Salesman salesman;

    public Sale() {}

    public Sale(LocalDate saleDate, float price, Salesman salesman) {
        this.saleDate = saleDate;
        this.price = price;
        this.salesman = salesman;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public String toString() {
        return id + ": " + saleDate.toString() + "| price: " + price; 
    }
}

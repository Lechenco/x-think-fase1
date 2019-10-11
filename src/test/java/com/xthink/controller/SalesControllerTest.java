package com.xthink.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SalesControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getIndex() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content()
                        .string(equalTo("Sales System: X-Think Store")));
        } catch (Exception e) {
            fail("Unespected answer to index");
        }
    }

    @Test
    public void newSale() {
        try {
            mvc.perform(MockMvcRequestBuilders.post("/insert")
                .param("salesmanId", "1").param("price", "100.0"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("0")));
        } catch (Exception e) {
            fail("Fail on insert new Sale in the Database" + 
                e.getLocalizedMessage());
        }
    }

    @Test
    public void listBetweenDates() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/list")
                .param("start", "2019-09-15").param("end", "2019-10-20"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                    "Nome: Maria Souza Total Vendas: 10 Med. Vendas por Dia: 0.2857142857142857\n" +
                    "Nome: Carlos Ribeiro Total Vendas: 6 Med. Vendas por Dia: 0.17142857142857143\n" + 
                    "Nome: Antonio João Total Vendas: 2 Med. Vendas por Dia: 0.05714285714285714\n")));
        } catch (Exception e) {
            fail("Error in list Between Dates" + e.getLocalizedMessage());
        }
    }

    @Test
    public void listBetweenCloseDates() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/list")
                .param("start", "2019-10-01").param("end", "2019-10-10"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(
                    "Nome: Maria Souza Total Vendas: 4 Med. Vendas por Dia: 0.4444444444444444\n" +
                    "Nome: Carlos Ribeiro Total Vendas: 2 Med. Vendas por Dia: 0.2222222222222222\n" + 
                    "Nome: Antonio João Total Vendas: 2 Med. Vendas por Dia: 0.2222222222222222\n")));
        } catch (Exception e) {
            fail("Error in list Between Close Dates" + e.getLocalizedMessage());
        }
    }

    @Test
    public void listBetweenDatesEmpty() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/list")
                .param("start", "2019-01-01").param("end", "2019-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
        } catch (Exception e) {
            fail("List Between Dates throws Exception");
        }
    }
    
}

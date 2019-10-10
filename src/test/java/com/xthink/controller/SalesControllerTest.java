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
            mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(equalTo("Sales System: X-Think Store")));
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
                .param("start", "2019-01-01").param("end", "2019-12-31"))
                .andExpect(status().isOk());
        } catch (Exception e) {
            fail("Error in list Between Dates" + e.getLocalizedMessage());
        }
    }
    
}

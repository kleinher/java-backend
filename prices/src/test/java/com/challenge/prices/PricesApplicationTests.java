package com.challenge.prices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PricesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPriceById() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void testNotFound() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "0")
                        .param("brandId", "0")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testBadRequestProductId() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "a")
                        .param("brandId", "0")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBadRequestBrandId() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "0")
                        .param("brandId", "a")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBadRequestApplicationDate() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "0")
                        .param("brandId", "0")
                        .param("applicationDate", "a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetPriceAt10AMOn14th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void testGetPriceAt4PMOn14th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void testGetPriceAt9PMOn14th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void testGetPriceAt10AMOn15th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.5))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void testGetPriceAt9PMOn16th() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
}
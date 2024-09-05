package com.challenge.prices.infrastructure;

import com.challenge.prices.domain.PriceService;
import com.challenge.prices.domain.models.PriceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }
    private static long productId;
    private static long brandId;
    private static LocalDateTime applicationDate;
    private static String aplicationDateString;

    @BeforeAll
    public static void init() {
        productId = 1;
        brandId = 35455;
        aplicationDateString = "2024-09-02T12:00:00";
        applicationDate = LocalDateTime.parse(aplicationDateString);

    }

    @Test
    public void getPriceOk() throws Exception {
        PriceDTO priceDTO = PriceDTO.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(new BigDecimal("35.50"))
                .build();

        when(priceService.getPrice(productId,brandId,applicationDate)).thenReturn(Optional.of(priceDTO));

        mockMvc.perform(get("/api/prices")
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .param("applicationDate", aplicationDateString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value("35.5"));

    }

    @Test
    public void getPriceNotFound()throws Exception {
        when(priceService.getPrice(productId, brandId, applicationDate)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/prices")
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .param("applicationDate", aplicationDateString))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPriceException(){
        //TODO
    }

    @Test
    public void getPriceBadRequest() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "a")
                        .param("brandId", String.valueOf(brandId))
                        .param("applicationDate", aplicationDateString))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("Failed to convert 'productId' with value: 'a'"));
    }


}
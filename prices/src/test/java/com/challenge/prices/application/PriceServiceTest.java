package com.challenge.prices.application;


import com.challenge.prices.domain.PriceMapper;
import com.challenge.prices.domain.models.Price;
import com.challenge.prices.domain.models.PriceDTO;
import com.challenge.prices.infrastructure.repository.PriceRepositoryAdapter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    @Mock
    private PriceRepositoryAdapter priceRepositoryAdapter;
    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceService priceService;

    private static long productId;
    private static long brandId;
    private static LocalDateTime applicationDate;

    @BeforeAll
    public static void setUp() {
        productId = 1;
        brandId = 35455;
        applicationDate = LocalDateTime.parse("2020-06-16T21:00:00");

    }
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPriceOk() {
        Integer priceList = 1;
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59");

        LocalDateTime testDate = LocalDateTime.parse("2020-06-14T10:00:00");

        Price price = Price.builder()
            .id(0L)
            .brandId(brandId)
            .startDate(startDate)
            .endDate(endDate)
            .priceList(priceList)
            .productId(productId)
            .priority(0)
            .price(new BigDecimal("35.50"))
            .currency("EUR")
            .build();

        PriceDTO priceDTO = PriceDTO.builder()
            .productId(productId)
            .brandId(brandId)
            .priceList(priceList)
            .startDate(startDate)
            .endDate(endDate)
            .price(new BigDecimal("35.50"))
            .build();

        when(priceRepositoryAdapter.getPrice(productId, brandId, testDate)).thenReturn(Optional.of(price));
        when(priceMapper.toDto(price)).thenReturn(priceDTO);

        Optional<PriceDTO> result = priceService.getPrice(productId, brandId, testDate);
        assert(result.isPresent());
        assertEquals(priceDTO.getProductId(), result.get().getProductId());
        assertEquals(priceDTO.getBrandId(), result.get().getBrandId());
        assertEquals(priceDTO.getPriceList(), result.get().getPriceList());
        assertEquals(priceDTO.getStartDate(), result.get().getStartDate());
        assertEquals(priceDTO.getEndDate(), result.get().getEndDate());
        assertEquals(priceDTO.getPrice(), result.get().getPrice());
    }

    @Test
    public void testGetPriceNotFound() {
        when(priceRepositoryAdapter.getPrice(productId, brandId, applicationDate)).thenReturn(Optional.empty());

        Optional<PriceDTO> result = priceService.getPrice(productId, brandId, applicationDate);
        assert(result.isEmpty());
    }

}

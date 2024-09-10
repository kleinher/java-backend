package com.challenge.prices.infrastructure.repository;

import com.challenge.prices.domain.models.Brand;
import com.challenge.prices.domain.models.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PricesRepositoryAdapterTest {

    @Mock
    private JPAPriceRepository jpaPriceRepository;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPriceFound() {
        // Given
        long productId = 1;
        long brandId = 35455;
        LocalDateTime applicationDate = LocalDateTime.parse("2024-09-02T12:00:00");

        Prices prices = Prices.builder()
                .id(1L)
                .productId(productId)
                .brand(new Brand(35455L, "ZARA", new ArrayList<Prices>()))
                .priceList(1)
                .priority(0)
                .startDate(LocalDateTime.parse("2024-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2024-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();

        when(jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId,brandId,applicationDate,applicationDate)).thenReturn(Optional.of(prices));
        // When
        Optional<Prices> retrivedPrice = priceRepositoryAdapter.getPrice(productId, brandId, applicationDate);

        // Then
        assertTrue(retrivedPrice.isPresent());
        assertEquals(retrivedPrice.get().getProductId(), productId);
        assertEquals(retrivedPrice.get().getBrand().getId(), brandId);
        assertEquals( 1,retrivedPrice.get().getPriceList());
        assertEquals( 0,retrivedPrice.get().getPriority());
        assertEquals(retrivedPrice.get().getStartDate(), LocalDateTime.parse("2024-06-14T00:00"));
        assertEquals(retrivedPrice.get().getEndDate(), LocalDateTime.parse("2024-12-31T23:59:59"));
        assertEquals(retrivedPrice.get().getPrice(), BigDecimal.valueOf(35.50));
        assertEquals("EUR",retrivedPrice.get().getCurrency());

    }

    @Test
    void testPriceNotFound() {
        // Given
        long productId = 1;
        long brandId = 35455;
        LocalDateTime applicationDate = LocalDateTime.parse("2024-09-02T12:00:00");

        when(jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId,brandId,applicationDate,applicationDate)).thenReturn(Optional.empty());
        // When
        Optional<Prices> retrivedPrice = priceRepositoryAdapter.getPrice(productId, brandId, applicationDate);

        // Then
        assertTrue(retrivedPrice.isEmpty());
    }




}
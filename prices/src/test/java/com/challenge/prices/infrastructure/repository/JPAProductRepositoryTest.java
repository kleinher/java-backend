package com.challenge.prices.infrastructure.repository;

import com.challenge.prices.domain.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class JPAProductRepositoryTest {

    @Autowired
    private JPAPriceRepository jpaPriceRepository;

    @Test
    void testFindPriceInRepository() {
        // Given
        long productId = 1;
        long brandId = 35455;
        LocalDateTime applicationDate = LocalDateTime.parse("2024-09-02T12:00:00");

        Product product = Product.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .priority(0)
                .startDate(LocalDateTime.parse("2024-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2024-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();
        jpaPriceRepository.save(product);
        // When
        Optional<Product> retrivedPrice = jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate,applicationDate);

        // Then
        assertTrue(retrivedPrice.isPresent());
    }

    @Test
    void testFindPriceInRepositoryNotFound() {
        // Given
        long productId = 1;
        long brandId = 35455;
        LocalDateTime applicationDate = LocalDateTime.parse("2024-09-02T12:00:00");

        // When
        Optional<Product> retrivedPrice = jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate,applicationDate);

        // Then
        assertTrue(retrivedPrice.isEmpty());
    }
}

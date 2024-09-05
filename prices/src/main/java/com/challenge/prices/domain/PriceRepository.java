package com.challenge.prices.domain;

import com.challenge.prices.domain.models.Product;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Product> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}

package com.challenge.prices.domain;

import com.challenge.prices.domain.models.Brand;
import com.challenge.prices.domain.models.Prices;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Prices> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
package com.challenge.prices.domain;

import com.challenge.prices.domain.models.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}

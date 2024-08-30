package com.challenge.prices.domain.port.out;

import com.challenge.prices.domain.models.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPricePort {
    Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}

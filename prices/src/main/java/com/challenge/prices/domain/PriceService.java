package com.challenge.prices.domain;

import com.challenge.prices.infrastructure.controller.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<PriceDTO> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}